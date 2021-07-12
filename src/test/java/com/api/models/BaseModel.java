package com.api.models;

import com.google.gson.Gson;

public abstract class BaseModel implements Model {
    @Override
    public String toJson() {
        return new Gson().toJson(this);
    }
}
