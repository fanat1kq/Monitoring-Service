package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.exception.AuthorizeException;


import java.util.Collections;
import java.util.Optional;


/**
 * The user details service implementation
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository playerDAO;


    /**
     * @param username the username identifying the user whose data is required.
     * @return user details
     * @throws UsernameNotFoundException when user with username not found
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<org.example.model.User> optionalPlayer = playerDAO.findByLogin(username);
        if (optionalPlayer.isEmpty()) {
            throw new AuthorizeException("There is no player with this login in the database.");
        }
        org.example.model.User player = optionalPlayer.get();
        return new User(
                player.getLogin(),
                player.getPassword(),
                Collections.emptyList()
        );
    }
}
