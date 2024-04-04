package org.example.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.exception.AuthorizeException;
import org.example.in.dto.*;
import org.example.in.mappers.UserMapper;
import org.example.in.mappers.IndicationsMapper;
import org.example.service.UserService;
import org.example.service.ReadingService;

import java.util.List;

/**
 * controller for working with the player and his data
 */
@RestController
@RequestMapping("/reading")
@Validated
@Tag(name = "User controller", description = "User API")

public class ReadingController {

    private final UserService userService;
    private final ReadingService readingService;
    private final UserMapper userMapper;
    private final IndicationsMapper indicationsMapper;
    private SecurityContext securityContext;

@Autowired
    public ReadingController(UserService userService, ReadingService readingService, UserMapper userMapper, IndicationsMapper indicationsMapper) {
        this.userService = userService;
        this.readingService = readingService;
        this.userMapper = userMapper;
        this.indicationsMapper = indicationsMapper;
        this.securityContext = SecurityContextHolder.getContext();
    }
    public ReadingController(UserService userService, ReadingService readingService, UserMapper userMapper, IndicationsMapper indicationsMapper, SecurityContext securityContext) {
        this.userService = userService;
        this.readingService = readingService;
        this.userMapper = userMapper;
        this.indicationsMapper = indicationsMapper;
        this.securityContext = securityContext;
    }

    @Operation(summary  = "Return the player's transactions history")
    @GetMapping("/actual")
    public ResponseEntity<?> getActual(@RequestParam String login) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        org.example.model.User user = userService.getByLogin(login);
        List<IndicationsDTO> list = indicationsMapper.toDTOList(
                readingService.getActualHistory(user.getId()));
        return ResponseEntity.ok().body(new ReadingHistoryResponse(login, list));
    }

    /**
     * Get the player's transactions history
     *
     * @param login the player login
     * @return response entity
     */
    @Operation(summary  = "Return all history")
    @GetMapping("/history")
    public ResponseEntity<?> getHistory(@RequestParam String login) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        org.example.model.User user = userService.getByLogin(login);
        List<IndicationsDTO> list = indicationsMapper.toDTOList(
                readingService.getUserHistory(user.getId(),user.getRole()));
        return ResponseEntity.ok().body(new ReadingHistoryResponse(login, list));
    }

    @GetMapping("/date")
    @Operation(summary  = "Show all meter readings by date")
    public ResponseEntity<?> showAllMeterReadings(
            @RequestParam Integer year,
            @RequestParam Integer month,
            @RequestParam String login
    ) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        org.example.model.User user = userService.getByLogin(login);
        List<IndicationsDTO> list = indicationsMapper.toDTOList(
                readingService.getReadingsByMonthAndYear(year, month, user.getId()));
        return ResponseEntity.ok(new ReadingHistoryResponse(login, list));
    }


    @Operation(summary  = "add new reading")
    @PostMapping("/put")
    public ResponseEntity<?> put(@RequestBody @Valid PuttingRequest request) {
        if (!isValidLogin(request.getLogin())) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        org.example.model.User player = userService.getByLogin(request.getLogin());
        readingService.putReading(request.getName(), player.getId(), request.getValue(),request.getDate());
        return ResponseEntity.ok(new SuccessResponse("Putting reading completed successfully!"));
    }


    private boolean isValidLogin(String login) {
        if (securityContext.getAuthentication() == null) securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) throw new AuthorizeException("Unauthorized!");
        User principal = (User) authentication.getPrincipal();
        return principal.getUsername().equals(login);
    }
}
