package com.dfn.at.core.constants;

public class EnvironmentConstants {
    public static boolean HEADLESS = true;

    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 10;
    public static final int AT_INIT_WAIT = 12;

    public static final String REPORT_BASE_PATH = "target";
    public static final String CUCUMBER_REPORT_PATH = "target/cucumber-reports";
    public static final String REPORT_MERGE_PATH = "target/cucumber-html-reports";
    public static final String REPORT_ZIP_PATH = "target/at-automation-report.zip";
    public static final String REPORT_ZIP_DELIVERY_PATH = "target/delivery";
    public static final String SCREENSHOTS_PATH = "screenshots";

    public static String URL = "http://192.168.14.214/";
    public static String TITLE = "DirectFN AT Core";
    public static String BROWSER = "Chrome";

    // Users
    public static String USER_1 = "dy";
    public static String PASSWORD_1 = "123";
    public static String USER_2 = "dd";
    public static String PASSWORD_2 = "123";

    // Email Notification
    public static String MAIL_SUBJECT = "Automation Test Report";
    public static String MAIL_HOST = "email-smtp.us-east-1.amazonaws.com";
    public static int MAIL_HOST_PORT = 25;
    public static String FROM_MAIL = "no-reply@directfn.com";
    public static String TO_MAIL_LIST = "h.yashodha@directfn.com";

    // DB config constants
    public static final long POOL_CONNECTION_TIMEOUT = 15000;
    public static final long POOL_CONNECTION_IDLE_TIMEOUT = 30000;
    public static final int MAX_POOL_SIZE = 200;
    public static final int POOL_CONNECTION_MAX_LIFETIME = 1800000;
    public static final int MINIMUM_IDLE = 0;

    public static String SPRING_FILE_PATH = "core/META-INF/Automation-Core-Spring-Context.xml";
}