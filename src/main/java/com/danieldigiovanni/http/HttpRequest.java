package com.danieldigiovanni.http;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Represents an HTTP request with a body, query parameters, and headers.
 */
@Getter
@Builder
public class HttpRequest {

    private String body;
    private Map<String, String> queryParameters;
    private Map<String, String> headers;

}
