package org.example.repository;

import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The player repository
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find player in database by login
     *
     * @param login the login
     * @return optional of player. If not fount optional is empty
     */
    Optional<User> findByLogin(String login);
}
