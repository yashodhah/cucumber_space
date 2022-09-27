package com.dfn.at.common.services;

public class TestLinkLogHandler {
    private static LogHandler logHandler = new LogHandler(TestLinkLogHandler.class.getName());

    public static void log(String testLinkId) {
        logHandler.logInfo(testLinkId);
    }
}
