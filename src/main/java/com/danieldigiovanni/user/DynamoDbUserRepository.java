package com.danieldigiovanni.user;

import java.util.Optional;

public class DynamoDbUserRepository implements UserRepository {

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

}
