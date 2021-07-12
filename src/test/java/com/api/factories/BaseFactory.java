package com.api.factories;

import com.User;
import com.api.ApiHelper;
import io.restassured.response.Response;

import static com.api.ApiHelper.*;

public abstract class BaseFactory implements Factory {
    public BaseFactory() {
        ApiHelper.setBaseUrl(User.baseURI);
        ApiHelper.setToken(User.token);
    }

    @Override
    public Response get() {
        return getRequest();
    }

    @Override
    public Response get(String id) {
        return getRequest(id);
    }

    @Override
    public Response delete(String id) {
        return deleteRequest(id);
    }

    @Override
    public Response create(String payload) {
        return postRequest(payload);
    }

    @Override
    public Response update(String payload, String id) {
        return putRequest(payload, id);
    }
}
