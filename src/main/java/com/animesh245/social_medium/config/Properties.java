package com.animesh245.social_medium.config;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

public class Properties
{
    public static final Resource TEMP = new ByteArrayResource(System.getProperty("java.io.tmpdir").getBytes());

    public static final int MAX_UPLOAD_SIZE = (1 * 1024 * 1024) * 100; // 1MB * 10 = 10 MB

    public static final String WRITE_PATH = System.getProperty("user.home") + "/Documents/Projects/Social_Medium/src/main/webapp/WEB-INF/resources/image/";

    public static final String USER_FOLDER = "user/";
    public static final String STATUS_FOLDER = "status/";
}
