package com.dfn.at.common.services;

public interface LogHandlerI {
    void logInfo(String message);

    void logError(String message);

    void logError(String message, Throwable e);

    void logDebug(String message);
}
