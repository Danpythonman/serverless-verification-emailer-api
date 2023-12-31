package com.danieldigiovanni.factory.controller;

import com.danieldigiovanni.factory.exception.InvalidHttpControllerEnumException;
import com.danieldigiovanni.http.HttpController;
import com.danieldigiovanni.http.HttpControllerEnum;

/**
 * Factory for creating {@link HttpController} objects.
 */
public class HttpControllerFactory {

    /**
     * Creates an HTTP controller.
     *
     * @param httpControllerEnum The specific HTTP controller to create.
     *
     * @return The HTTP controller corresponding to the given enum value.
     *
     * @throws InvalidHttpControllerEnumException If the given
     *                                            {@link HttpControllerEnum}
     *                                            value could not be matched to
     *                                            a controller.
     */
    public HttpController httpController(HttpControllerEnum httpControllerEnum) {
        switch (httpControllerEnum) {
            case CREATE_USER_HTTP_CONTROLLER:
                return CreateUserHttpControllerFactory.instance().createUserHttpController();
            default:
                throw new InvalidHttpControllerEnumException(httpControllerEnum);
        }
    }

}
