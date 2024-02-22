package org.example.service;

import org.example.dao.UserDAO;
import org.example.exception.PlayerNotFoundException;
import org.example.model.User;


import java.math.BigDecimal;
import java.util.Optional;

/**
 * The type Player service.
 */

//@Loggable
public class UserService  {

    private final UserDAO userDAO;

    /**
     * Instantiates a new Player service.
     *
     * @param playerDAO the player dao
     */
    public UserService(UserDAO playerDAO) {
        this.userDAO = playerDAO;
    }

//    @Audit


    public User getByLogin(String login) {
        Optional<User> optionalPlayer = userDAO.findByLogin(login);
        return optionalPlayer.orElseThrow(() -> new PlayerNotFoundException("Player with login " + login + " not found!"));
    }
}
