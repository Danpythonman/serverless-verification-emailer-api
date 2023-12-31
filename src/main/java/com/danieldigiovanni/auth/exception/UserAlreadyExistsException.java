package com.danieldigiovanni.auth.exception;

/**
 * Exception representing the case of attempting to create a user with an email
 * already in use by a user.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a {@link UserAlreadyExistsException} with the email that
     * caused the exception.
     * <p>
     * The provided email will be used in the error message.
     *
     * @param email The email that caused the exception.
     */
    public UserAlreadyExistsException(String email) {
        super(String.format("User with email %s already exists", email));
    }

}
