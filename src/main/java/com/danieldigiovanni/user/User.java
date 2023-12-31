package com.danieldigiovanni.user;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * Represents a user in the database.
 */
@Getter
@Builder
public class User {

    private String email;
    private String name;
    private Boolean isVerified;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLogin;

}
