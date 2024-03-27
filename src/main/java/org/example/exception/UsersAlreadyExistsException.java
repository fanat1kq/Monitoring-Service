package org.example.exception;

/**
 * The type Player already exists exception.
 */
public class UsersAlreadyExistsException extends RuntimeException {
    /**
     * Instantiates a new Player already exists exception.
     *
     * @param message the message
     */
    public UsersAlreadyExistsException(String message) {
        super(message);
    }
}
