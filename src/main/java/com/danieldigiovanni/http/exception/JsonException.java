package com.danieldigiovanni.http.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Exception representing a failure in JSON processing.
 * <p>
 * The only purpose for this exception existing is to convert the
 * {@link JsonProcessingException} thrown by
 * {@link ObjectMapper#readValue(String, Class)} and
 * {@link ObjectMapper#writeValueAsString(Object)} to an unchecked exception.
 */
public class JsonException extends RuntimeException {

    /**
     * Constructs a {@link JsonException} with the given cause.
     *
     * @param cause The throwable that caused the exception.
     */
    public JsonException(Throwable cause) {
        super(cause);
    }

}
