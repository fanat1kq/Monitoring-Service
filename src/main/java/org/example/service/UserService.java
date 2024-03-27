package org.example.service;

import org.example.model.User;

import java.math.BigDecimal;

/**
 * The interface Player service.
 */
public interface UserService {


    /**
     * Get player by login.
     *
     * @param login the login
     * @return the player entity
     */
    User getByLogin(String login);

}
