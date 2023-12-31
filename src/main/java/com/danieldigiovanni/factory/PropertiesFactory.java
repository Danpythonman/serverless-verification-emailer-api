package com.danieldigiovanni.factory;

import com.danieldigiovanni.factory.exception.PropertyLoadingException;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Factory for loading in properties. The properties will be stored in this
 * class so that properties will only be loaded in once.
 * <p>
 * The properties file that is loaded is {@code application.properties}.
 */
@Log4j2
public class PropertiesFactory {

    private Properties properties = null;

    private static PropertiesFactory instance = null;

    private PropertiesFactory() { /* Private constructor for singleton */ }

    /**
     * Gets the singleton instance of the {@link PropertiesFactory}, creating
     * it if it does not yet exist.
     *
     * @return The singleton instance of the {@link PropertiesFactory}.
     */
    public static PropertiesFactory instance() {
        if (instance == null) {
            instance = new PropertiesFactory();
        }
        return instance;
    }

    /**
     * Get a property from the properties file.
     *
     * @param key The key of the property.
     *
     * @return The property associated with the given key.
     */
    public String getProperty(String key) {
        if (this.properties == null) {
            this.loadProperties();
        }
        return this.properties.getProperty(key);
    }

    /**
     * Load in the properties file ({@code application.properties}).
     */
    private void loadProperties() {
        log.info("Loading properties");
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            throw new PropertyLoadingException(e);
        }
        this.properties = props;
        log.info("Properties loaded");
    }

}
