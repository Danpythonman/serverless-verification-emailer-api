package com.danieldigiovanni.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.danieldigiovanni.factory.controller.HttpControllerFactory;
import com.danieldigiovanni.http.HttpController;
import com.danieldigiovanni.http.HttpControllerEnum;
import lombok.extern.log4j.Log4j2;

/**
 * The base AWS Lambda handler for AWS API Gateway events.
 */
@Log4j2
public abstract class AwsApiGatewayLambdaHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayV2HTTPResponse> {

    /**
     * The controller that will process the API Gateway event.
     */
    protected final AwsApiGatewayController controller;

    /**
     * Creates an API Gateway Lambda handler with a specific controller. The
     * controller is specified by the {@link HttpControllerEnum} enum.
     * <p>
     * When implementing a specific handler, all you need to do is implement an
     * {@link HttpController} and call this constructor with the right enum
     * value. The rest is handled by this code and AWS.
     *
     * @param httpControllerEnum The specific {@link HttpController} to create.
     */
    protected AwsApiGatewayLambdaHandler(HttpControllerEnum httpControllerEnum) {
        log.info("Creating controller " + httpControllerEnum);

        HttpControllerFactory factory = new HttpControllerFactory();
        HttpController httpController = factory.httpController(httpControllerEnum);

        this.controller = new AwsAwsApiGatewayToHttpAdapter(httpController);
    }

    /**
     * Handles an API Gateway event.
     * <p>
     * The template method design pattern is used. The only step that changes is
     * the actual processing of the event. This is done by the
     * {@code controller} property of this class which processes the event. The
     * controller is specified in the constructor of this class
     * {@link AwsApiGatewayLambdaHandler#AwsApiGatewayLambdaHandler(HttpControllerEnum)}
     * by the supplied {@link HttpControllerEnum} argument.
     *
     * @param event   The event from API Gateway.
     * @param context The Lambda Function context.
     *
     * @return The response to return to API Gateway.
     */
    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        // Step 1: Log before handling.
        this.logBeforeHandling(event);

        // Step 2 (template step): Handle the event.
        // This is handled by the controller that was created in the constructor
        // of this class.
        APIGatewayV2HTTPResponse response = this.controller.execute(event);

        // Step 3: Log after handling.
        this.logAfterHandling(event, response.getStatusCode());

        // Step 4: Return response to API Gateway.
        return response;
    }

    /**
     * Log the API Gateway event before processing.
     *
     * @param event The API gateway event about to be processed.
     */
    private void logBeforeHandling(APIGatewayProxyRequestEvent event) {
        log.info(
            "Handling {} request to {}",
            event.getHttpMethod(),
            event.getPath()
        );
    }

    /**
     * Log the API Gateway event after processing.
     *
     * @param event  The API Gateway event that has just been processed.
     * @param status The HTTP status being returned to API Gateway.
     */
    private void logAfterHandling(APIGatewayProxyRequestEvent event, int status) {
        log.info(
            "Successfully handled {} request to {} with status {}",
            event.getHttpMethod(),
            event.getPath(),
            status
        );
    }

}
