package com.dfn.at.common.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHandler implements LogHandlerI {
    private final Logger logger;

    public LogHandler(String className) {
        this.logger = LogManager.getLogger(className);
    }

    public LogHandler(Class className) {
        this.logger = LogManager.getLogger(className);
    }

    public void log(String message) {
        logger.info(message);
    }

    @Override
    public void logInfo(String message) {
        logger.info(message);
    }

    @Override
    public void logError(String message) {
        logger.error(message);
    }

    @Override
    public void logError(String message, Throwable e) {
        logger.error(message, e);
    }

    @Override
    public void logDebug(String message) {
        logger.debug(message);
    }
}
