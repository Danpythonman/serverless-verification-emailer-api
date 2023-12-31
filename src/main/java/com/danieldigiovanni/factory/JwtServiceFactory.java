package com.danieldigiovanni.factory;

import com.danieldigiovanni.auth.JwtService;
import lombok.extern.log4j.Log4j2;

/**
 * Factory for creating the {@link JwtService} object. It will be stored in
 * this class once created so that only one {@link JwtService} instance will
 * ever be created.
 */
@Log4j2
public class JwtServiceFactory {

    private JwtService jwtService = null;

    private static JwtServiceFactory instance = null;

    private JwtServiceFactory() { }

    /**
     * Gets the singleton instance of the {@link JwtServiceFactory}, creating
     * it if it does not yet exist.
     *
     * @return The singleton instance of the {@link JwtServiceFactory}.
     */
    public static JwtServiceFactory instance() {
        if (instance == null) {
            instance = new JwtServiceFactory();
        }
        return instance;
    }

    /**
     * Gets the singleton instance of the {@link JwtService}, creating it if it
     * does not exist.
     *
     * @return The singleton {@link JwtService} instance.
     */
    public JwtService jwtService() {
        if (this.jwtService == null) {
            log.info("Creating JwtService");
            this.jwtService = new JwtService(
                Long.valueOf(PropertiesFactory.instance().getProperty("jwtDurationMillis")),
                PropertiesFactory.instance().getProperty("jwtSecretKey")
            );
        }
        return this.jwtService;
    }

}
