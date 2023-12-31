package com.danieldigiovanni.auth;

import com.danieldigiovanni.auth.dto.JwtResponse;
import com.danieldigiovanni.auth.dto.RegisterRequest;
import com.danieldigiovanni.user.User;
import com.danieldigiovanni.user.UserRepository;
import com.danieldigiovanni.auth.exception.UserAlreadyExistsException;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Optional;

/**
 * Handles creating authenticating users.
 */
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Create a users.
     *
     * @param registerRequest The request to register a user.
     *
     * @return A JWT for the user to log in with.
     *
     * @throws UserAlreadyExistsException If a user with the provided email
     *                                    already exists.
     */
    public JwtResponse register(RegisterRequest registerRequest) throws UserAlreadyExistsException {
        Optional<User> existingCustomer =
            this.userRepository.findByEmail(registerRequest.getEmail());

        if (existingCustomer.isPresent()) {
            throw new UserAlreadyExistsException(registerRequest.getEmail());
        }

        Date now = new Date();

        User user = User.builder()
            .email(registerRequest.getEmail())
            .name(registerRequest.getName())
            .isVerified(false)
            .createdAt(now)
            .updatedAt(now)
            .lastLogin(now)
            .build();

        user = this.userRepository.save(user);

        String jwt = this.jwtService.generateToken(user);

        return new JwtResponse(jwt);
    }

}
