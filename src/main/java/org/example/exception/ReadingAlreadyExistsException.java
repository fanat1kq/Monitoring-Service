package org.example.exception;

/**
 * The type Transaction already exists exception.
 */
public class ReadingAlreadyExistsException extends RuntimeException {
    /**
     * Instantiates a new Transaction already exists exception.
     *
     * @param message the message
     */
    public ReadingAlreadyExistsException(String message) {
        super(message);
    }
}
