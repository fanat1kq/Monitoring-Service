package org.example.in.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.example.in.dto.SecurityRequestAuth;
import org.example.model.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.in.dto.JwtResponse;
import org.example.in.dto.UserDTO;
import org.example.in.dto.SecurityRequest;
import org.example.in.mappers.UserMapper;
import org.example.model.User;
import org.example.service.SecurityService;

import javax.validation.Valid;
/**
 * The security controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Validated
@Api(value = "SecurityController" , tags = {"Security Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Security Controller")
})
public class SecurityController {

    private final SecurityService securityService;
    private final UserMapper userMapper;

    /**
     * Authorization player in application
     *
     * @param dto the security request
     * @return response entity
     */
    @ApiOperation(value = "Return the JWT", response = JwtResponse.class, tags = "login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SecurityRequestAuth dto) {
        JwtResponse response = securityService.authorization(dto.login(), dto.password());
        return ResponseEntity.ok(response);
    }

    /**
     * Register the player in application
     *
     * @param dto the security request
     * @return response entity
     */
    @ApiOperation(value = "Return the player dto", response = UserDTO.class, tags = "registration")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody SecurityRequest dto) {
        User register = securityService.register(dto.login(), dto.password(), dto.role());
        return ResponseEntity.ok(userMapper.toDto(register));
    }
}
