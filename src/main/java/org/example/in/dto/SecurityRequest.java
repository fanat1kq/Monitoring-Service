package org.example.in.dto;

import jakarta.validation.constraints.NotNull;
import org.example.model.Role;


/**
 * Request data for security
 *
 * @param login the player login
 * @param password the player password
 */
//проверка на нал при регистрации
public record SecurityRequest(@NotNull(message = "Login must be not null.") String login,
                              @NotNull(message = "Password must be not null.") String password,
                              @NotNull(message = "Role must be not null.") Role role) {
}
