package com.danieldigiovanni.lambda.handler;

import com.danieldigiovanni.http.HttpControllerEnum;
import com.danieldigiovanni.lambda.AwsApiGatewayLambdaHandler;

/**
 * Lambda handler for creating a user.
 */
public class CreateUserHandler extends AwsApiGatewayLambdaHandler {

    public CreateUserHandler() {
        super(HttpControllerEnum.CREATE_USER_HTTP_CONTROLLER);
    }

}
