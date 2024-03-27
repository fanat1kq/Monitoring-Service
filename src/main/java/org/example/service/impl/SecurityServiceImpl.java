package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.aop.Audit;

import org.example.exception.AuthorizeException;
import org.example.exception.RegisterException;
import org.example.in.security.JwtTokenProvider;
import org.example.model.Role;
import org.example.model.User;
import org.example.in.dto.JwtResponse;
import org.example.repository.UserRepository;
import org.example.service.SecurityService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * The type Security service.
 */
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userDAO;

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;



    @Transactional
    @Audit
    @Override
    public User register(String login, String password, Role role) {
        Optional<User> user = userDAO.findByLogin(login);
        if (user.isPresent()) {
            throw new RegisterException("The user with this login already exists.");
        }
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setRole(role);
        newUser.setPassword(passwordEncoder.encode(password));


        return userDAO.save(newUser);
    }
    @Transactional
    @Audit
    @Override
    public JwtResponse authorization(String login, String password) {

        Optional<User> optionalPlayer = userDAO.findByLogin(login);
        if (optionalPlayer.isEmpty()) {
            throw new AuthorizeException("There is no player with this login in the database.");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        String accessToken = tokenProvider.createAccessToken(login);
        String refreshToken = tokenProvider.createRefreshToken(login);
        return new JwtResponse(login, accessToken, refreshToken);
    }
    }

