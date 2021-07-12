package com.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiHelper {
    private static Logger logger = LogManager.getLogger(ApiHelper.class);
    private static RequestSpecification request;
    private static String baseURI;
    private static String basePath;
    private static String token;

    private static void basicAuth() {
        RestAssured.baseURI = baseURI;
        RestAssured.basePath = basePath;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
        if (token != null)
            request.header("Authorization", "Token " + token);
    }

    public static Response postRequest(String data) {
        logger.info("POST " + baseURI + basePath);
        try {
            basicAuth();
            return responseHandler(request.body(data).post());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Response postRequest(String data, String endpoint) {
        logger.info("POST " + RestAssured.baseURI + RestAssured.basePath + endpoint);
        try {
            basicAuth();
            return responseHandler(request.body(data).post(endpoint));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Response putRequest(String data, String endpoint) {
        logger.info("PUT " + RestAssured.baseURI + RestAssured.basePath + endpoint);
        try {
            basicAuth();
            return responseHandler(request.body(data).put(endpoint));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Response deleteRequest(String id) {
        logger.info("DELETE " + RestAssured.baseURI + RestAssured.basePath + id);
        try {
            basicAuth();
            return responseHandler(request.delete(id));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Response getRequest() {
        logger.info("GET " + RestAssured.baseURI + RestAssured.basePath);
        try {
            basicAuth();
            return responseHandler(request.get());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    public static Response getRequest(String id) {
        logger.info("GET " + RestAssured.baseURI + RestAssured.basePath + id);
        try {
            basicAuth();
            return responseHandler(request.get(id));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage() + "\n");
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    private static Response responseHandler(Response response) {
        if (String.valueOf(response.statusCode()).matches("^[45]\\d{2}$")) {
            logger.warn(response.statusLine() + " (" + response.time() + "ms)\n");
            return response;
        } else {
            logger.info(response.statusLine() + " (" + response.time() + "ms)\n");
            return response;
        }
    }


    public static void setBaseUrl(String url) {
        ApiHelper.baseURI = url;
    }

    public static void setBasePath(String path) {
        ApiHelper.basePath = path;
    }

    public static void setToken(String token) {
        ApiHelper.token = token;
    }
}
