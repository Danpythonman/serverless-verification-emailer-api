package com.danieldigiovanni.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

/**
 * Processor for API Gateway events.
 */
public interface AwsApiGatewayController {

    /**
     * Processes an API Gateway event.
     *
     * @param event The API Gateway event to be processed.
     *
     * @return The response to return to API Gateway.
     */
    APIGatewayV2HTTPResponse execute(APIGatewayProxyRequestEvent event);

}
