package com.danieldigiovanni.http.controller;

import com.danieldigiovanni.auth.AuthService;
import com.danieldigiovanni.auth.dto.JwtResponse;
import com.danieldigiovanni.auth.dto.RegisterRequest;
import com.danieldigiovanni.http.HttpController;
import com.danieldigiovanni.http.HttpRequest;
import com.danieldigiovanni.http.HttpResponse;
import com.danieldigiovanni.http.HttpStatus;
import com.danieldigiovanni.http.exception.JsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

/**
 * HTTP controller for handling requests to create a user.
 */
@AllArgsConstructor
public class CreateUserHttpController extends HttpController {

    private final AuthService authService;

    @Override
    public HttpResponse execute(HttpRequest httpRequest) {
        RegisterRequest request;
        try {
            request = this.objectMapper.readValue(httpRequest.getBody(), RegisterRequest.class);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }

        JwtResponse response = this.authService.register(request);

        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }

        return HttpResponse.builder()
            .body(jsonResponse)
            .status(HttpStatus.CREATED)
            .build();
    }

}
