package com.api.factories;

import com.User;
import com.api.ApiHelper;
import com.api.models.TokenModel;
import io.restassured.response.Response;

public class TokenFactory extends BaseFactory {
    public TokenFactory() {
        ApiHelper.setBasePath("/api-token-auth/gen/");
    }

    public Response create() {
        TokenModel model = new TokenModel();
        model.username = User.username;
        model.password = User.password;
        String payload = model.toJson();
        return ApiHelper.postRequest(payload);
    }
}
