package com.animesh245.social_medium.config;

public class Properties
{
    public static final String TEMP = System.getProperty("java.io.tmpdir");

    public static final int MAX_UPLOAD_SIZE = (1 * 1024 * 1024) * 20; // 1MB * 10 = 10 MB

    public static final String WRITE_PATH = System.getProperty("user.home") + "/Documents/Projects/Social_Medium/src/main/webapp/WEB-INF/resources/image/";

    public static final String USER_FOLDER = "user/";
    public static final String STATUS_FOLDER = "status/";
}
