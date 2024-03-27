package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.exception.PlayerNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

/**
 * The type Player service.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements org.example.service.UserService {

    private final UserRepository userDAO;


    @Transactional(readOnly = true)
    @Override
    public User getByLogin(String login) {
        Optional<User> optionalPlayer = userDAO.findByLogin(login);
        return optionalPlayer.orElseThrow(() -> new PlayerNotFoundException("Player with login " + login + " not found!"));
    }
}
