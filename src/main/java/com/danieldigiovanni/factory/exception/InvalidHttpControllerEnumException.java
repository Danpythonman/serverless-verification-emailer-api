package com.danieldigiovanni.factory.exception;

import com.danieldigiovanni.factory.controller.HttpControllerFactory;
import com.danieldigiovanni.http.HttpController;
import com.danieldigiovanni.http.HttpControllerEnum;

/**
 * Exception to throw when an {@link HttpControllerEnum} value could not be
 * matched to an {@link HttpController} in the method
 * {@link HttpControllerFactory#httpController(HttpControllerEnum)}
 */
public class InvalidHttpControllerEnumException extends RuntimeException {

    /**
     * Constructs an {@link InvalidHttpControllerEnumException} with the
     * {@link HttpControllerEnum} value that caused the exception.
     *
     * @param httpControllerEnum The {@link HttpControllerEnum} value that could
     *                           not be matched to a controller.
     */
    public InvalidHttpControllerEnumException(HttpControllerEnum httpControllerEnum) {
        super(String.format(
            "HttpControllerEnum value %s could not be matched to an HTTP controller",
            httpControllerEnum.name()
        ));
    }

}
