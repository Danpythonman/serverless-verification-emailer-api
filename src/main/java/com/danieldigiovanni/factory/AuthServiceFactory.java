package com.danieldigiovanni.factory;

import com.danieldigiovanni.auth.AuthService;
import lombok.extern.log4j.Log4j2;

/**
 * Factory for creating the {@link AuthService} object. It will be stored in
 * this class once created so that only one {@link AuthService} instance will
 * ever be created.
 */
@Log4j2
public class AuthServiceFactory {

    private AuthService authService = null;

    private static AuthServiceFactory instance = null;

    private AuthServiceFactory() { /* Private constructor for singleton */ }

    /**
     * Gets the singleton instance of the {@link AuthServiceFactory}, creating
     * it if it does not yet exist.
     *
     * @return The singleton instance of the {@link AuthServiceFactory}.
     */
    public static AuthServiceFactory instance() {
        if (instance == null) {
            instance = new AuthServiceFactory();
        }
        return instance;
    }

    /**
     * Gets the singleton instance of the {@link AuthService}, creating it if it
     * does not exist.
     *
     * @return The singleton {@link AuthService} instance.
     */
    public AuthService authService() {
        if (this.authService == null) {
            log.info("Creating AuthService");
            this.authService = new AuthService(
                DynamoDbUserRepositoryFactory.instance().dynamoDbUserRepository(),
                JwtServiceFactory.instance().jwtService()
            );
        }
        return this.authService;
    }

}
