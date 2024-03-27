package org.example.service;

import org.example.model.Role;
import org.example.model.User;
import org.example.in.dto.JwtResponse;

/**
 * The interface security service.
 */
public interface SecurityService {

    /**
     * Register the player in application
     *
     * @param login the login
     * @param password the password
     * @return the registered player
     */
    User register(String login, String password, Role role);

    /**
     * Authorization player in application
     *
     * @param login the login
     * @param password the password
     * @return jwt response
     */
    JwtResponse authorization(String login, String password);
}
