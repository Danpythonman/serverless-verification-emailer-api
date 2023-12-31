package com.danieldigiovanni.factory;

import com.danieldigiovanni.user.DynamoDbUserRepository;
import lombok.extern.log4j.Log4j2;

/**
 * Factory for creating the {@link DynamoDbUserRepository} object. It will be stored in
 * this class once created so that only one {@link DynamoDbUserRepository} instance will
 * ever be created.
 */
@Log4j2
public class DynamoDbUserRepositoryFactory {

    private DynamoDbUserRepository dynamoDbUserRepository = null;

    private static DynamoDbUserRepositoryFactory instance = null;

    private DynamoDbUserRepositoryFactory() { }

    /**
     * Gets the singleton instance of the {@link DynamoDbUserRepositoryFactory}, creating
     * it if it does not yet exist.
     *
     * @return The singleton instance of the {@link DynamoDbUserRepositoryFactory}.
     */
    public static DynamoDbUserRepositoryFactory instance() {
        if (instance == null) {
            instance = new DynamoDbUserRepositoryFactory();
        }
        return instance;
    }

    /**
     * Gets the singleton instance of the {@link DynamoDbUserRepository}, creating
     * it if it does not exist.
     *
     * @return The singleton instance of the {@link DynamoDbUserRepository}.
     */
    public DynamoDbUserRepository dynamoDbUserRepository() {
        if (this.dynamoDbUserRepository == null) {
            log.info("Creating DynamoDbUserRepository");
            this.dynamoDbUserRepository = new DynamoDbUserRepository();
        }
        return this.dynamoDbUserRepository;
    }

}
