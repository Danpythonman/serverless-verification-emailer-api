package com.danieldigiovanni.http;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * HTTP request processor.
 */
public abstract class HttpController {

    /**
     * Object mapper for parsing JSON.
     * <p>
     * This is declared in the parent class so that it doesn't need to be
     * declared in multiple subclasses.
     */
    protected final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Processes an HTTP request.
     *
     * @param request The HTTP request to be processed.
     *
     * @return The HTTP response to send back.
     */
    abstract public HttpResponse execute(HttpRequest request);

}
