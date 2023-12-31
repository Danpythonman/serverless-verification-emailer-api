package com.danieldigiovanni.http;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Represents an HTTP response with a status code, body, and headers.
 */
@Getter
@Builder
public class HttpResponse {

    private Integer status;
    private String body;
    private Map<String, String> headers;

}
