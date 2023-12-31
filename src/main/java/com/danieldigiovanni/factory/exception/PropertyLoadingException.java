package com.danieldigiovanni.factory.exception;

import java.io.IOException;
import java.io.InputStream;

/**
 * Exception representing a failure when loading in properties.
 * <p>
 * The only purpose for this exception existing is to convert the
 * {@link IOException} thrown by {@link java.util.Properties#load(InputStream)}
 * to an unchecked exception.
 */
public class PropertyLoadingException extends RuntimeException {

    /**
     * Constructs a {@link PropertyLoadingException} with the given cause.
     *
     * @param cause The throwable that caused the exception.
     */
    public PropertyLoadingException(Throwable cause) {
        super(cause);
    }

}
