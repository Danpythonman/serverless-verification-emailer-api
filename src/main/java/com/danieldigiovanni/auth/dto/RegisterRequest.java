package com.danieldigiovanni.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

/**
 * DTO representing the data that needs to be sent for a user to register.
 */
@Setter
@Getter
@Builder
@Jacksonized
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String confirmPassword;

}
