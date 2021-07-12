package com.utils;

import com.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Helpers {
    private static Logger logger = LogManager.getLogger(Helpers.class);

    public static Object getModel(String fileName, Class classType) {
        try {
            logger.info("Opening file " + User.JSON_DATA + fileName);
            return new Gson().fromJson(new FileReader(User.JSON_DATA + fileName), classType);
        } catch (FileNotFoundException e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static JsonObject parseJson(Response response) {
        try {
            return new Gson().fromJson(response.asString(), JsonObject.class);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
}
