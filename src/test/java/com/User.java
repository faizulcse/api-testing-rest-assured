package com;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    public static final String CURRENT_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
    public static final String ROOT_DIR = System.getProperty("user.dir");
    public static final String DATA_DIR = "/src/test/java/com/testData/";
    public static final String LOG_FILE = ROOT_DIR + "/logs/api-log.log";
    public static final String JSON_DATA = ROOT_DIR + DATA_DIR;

    public static final String TO_EMAIL = System.getenv("TO_EMAIL");
    public static final String FROM_EMAIL = System.getenv("FROM_EMAIL");
    public static final String EMAIL_PASSWORD = System.getenv("EMAIL_PASSWORD");

    public static String token;
    public static String username = "testdoc";
    public static String password = "Test123456";
    public static String baseURI = "https://nmed-c.zssbd.com";
}