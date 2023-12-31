package com.danieldigiovanni.factory.controller;

import com.danieldigiovanni.factory.AuthServiceFactory;
import com.danieldigiovanni.http.controller.CreateUserHttpController;
import lombok.extern.log4j.Log4j2;

/**
 * Factory for creating the {@link CreateUserHttpController} object. It will be stored in
 * this class once created so that only one {@link CreateUserHttpController} instance will
 * ever be created.
 */
@Log4j2
public class CreateUserHttpControllerFactory {

    private CreateUserHttpController createUserHttpController = null;

    private static CreateUserHttpControllerFactory instance = null;

    private CreateUserHttpControllerFactory() { /* Private constructor for singleton */ }

    /**
     * Gets the singleton instance of the {@link CreateUserHttpControllerFactory},
     * creating it if it does not yet exist.
     *
     * @return The singleton instance of the {@link CreateUserHttpControllerFactory}.
     */
    public static CreateUserHttpControllerFactory instance() {
        if (instance == null) {
            instance = new CreateUserHttpControllerFactory();
        }
        return instance;
    }

    /**
     * Gets the singleton instance of the {@link CreateUserHttpController}, creating
     * it if it does not exist.
     *
     * @return The singleton {@link CreateUserHttpController} instance.
     */
    CreateUserHttpController createUserHttpController() {
        if (this.createUserHttpController == null) {
            log.info("Creating CreateUserHttpController");
            this.createUserHttpController = new CreateUserHttpController(
                AuthServiceFactory.instance().authService()
            );
        }
        return this.createUserHttpController;
    }

}
