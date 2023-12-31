package com.danieldigiovanni.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.danieldigiovanni.http.HttpController;
import com.danieldigiovanni.http.HttpRequest;
import com.danieldigiovanni.http.HttpResponse;
import lombok.AllArgsConstructor;

/**
 * Adapts an {@link HttpController} to work with AWS API Gateway.
 */
@AllArgsConstructor
public class AwsAwsApiGatewayToHttpAdapter implements AwsApiGatewayController {

    /**
     * The HTTP controller that will process the HTTP request.
     */
    private final HttpController controller;

    /**
     * Processes an API Gateway event by converting it to an HTTP request,
     * processing the HTTP request, and converting the HTTP response to an API
     * gateway response.
     * <p>
     * The template method design pattern is used. The only step that changes is
     * the actual processing of the HTTP request. This is done by the
     * {@code controller} property of this class which processes the request.
     *
     * @param event The API Gateway event to be processed.
     *
     * @return The response to return to API Gateway.
     */
    @Override
    public APIGatewayV2HTTPResponse execute(APIGatewayProxyRequestEvent event) {
        // Step 1: Convert the API Gateway event to an HTTP request.
        HttpRequest request = HttpRequest.builder()
            .body(event.getBody())
            .headers(event.getHeaders())
            .queryParameters(event.getQueryStringParameters())
            .build();

        // Step 2 (template step): Handle the HTTP request (producing and HTTP
        // response).
        // This is handled by the HTTP controller that was passed into this
        // class in the constructor.
        HttpResponse response = this.controller.execute(request);

        // Step 3: Convert the HTTP response to an API Gateway response.
        return APIGatewayV2HTTPResponse.builder()
            .withBody(response.getBody())
            .withStatusCode(response.getStatus())
            .withHeaders(response.getHeaders())
            .build();
    }

}
