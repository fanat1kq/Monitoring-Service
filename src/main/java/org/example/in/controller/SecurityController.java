package org.example.in.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.in.dto.JwtResponse;
import org.example.in.dto.SecurityRequest;
import org.example.in.mappers.UserMapper;
import org.example.model.User;
import org.example.service.SecurityService;

/**
 * The security controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth controller", description = "Auth API")
@Validated
public class SecurityController {

    private final SecurityService securityService;
    private final UserMapper userMapper;

    /**
     * Authorization player in application
     *
     * @param dto the security request
     * @return response entity
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SecurityRequest dto) {
        JwtResponse response = securityService.authorization(dto.login(), dto.password());
        return ResponseEntity.ok(response);
    }

    /**
     * Register the player in application
     *
     * @param dto the security request
     * @return response entity
     */
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody SecurityRequest dto) {
        User register = securityService.register(dto.login(), dto.password(), dto.role());
        return ResponseEntity.ok(userMapper.toDto(register));
    }
}
