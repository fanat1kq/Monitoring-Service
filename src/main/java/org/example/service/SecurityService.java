package org.example.service;

import org.example.dao.UserDAO;

import org.example.exception.AuthorizeException;
import org.example.in.security.JwtTokenProvider;
import org.example.model.Role;
import org.example.model.User;
import org.example.in.dto.JwtResponse;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

/**
 * The type Security service.
 */
public class SecurityService {

    private final UserDAO userDAO;

    private final JwtTokenProvider tokenProvider;

    /**
     * Instantiates a new Security service.
     *
     * @param userDAO the player dao
     */
    public SecurityService(UserDAO userDAO, JwtTokenProvider tokenProvider) {
        this.userDAO = userDAO;
        this.tokenProvider = tokenProvider;
    }

//    @Audit
    public User register(String login, String password, String role) {
//        Optional<User> user = userDAO.findByLogin(login);
//        if (user.isPresent()) {
//            throw new RegisterException("The user with this login already exists.");
//        }

        User newUser = new User();
        newUser.setName(login);
        newUser.setRole(Role.valueOf(role));
        newUser.setPassword(password);

        return userDAO.createUser(newUser);
    }

//    @Audit

    public JwtResponse authorization(String login, String password) {
//        Optional<User> optionalPlayer = userDAO.findByLogin(login);
//        if (optionalPlayer.isEmpty()) {
//            throw new AuthorizeException("There is no user with this login in the database.");
//        }

//        User user = optionalPlayer.get();
//        if (!user.getPassword().equals(password)) {
//            throw new AuthorizeException("Incorrect password.");
//        }

        String accessToken = tokenProvider.createAccessToken(login);
        String refreshToken = tokenProvider.createRefreshToken(login);
        try {
            tokenProvider.authentication(accessToken);
        } catch (AccessDeniedException e) {
            throw new AuthorizeException("Access denied!.");
        }
        return new JwtResponse(login, accessToken, refreshToken);
    }
}
