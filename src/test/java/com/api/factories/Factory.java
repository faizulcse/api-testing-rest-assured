package com.api.factories;

import io.restassured.response.Response;

public interface Factory {
    Response get();

    Response get(String id);

    Response delete(String id);

    Response create(String payload);

    Response update(String payload, String id);
}
