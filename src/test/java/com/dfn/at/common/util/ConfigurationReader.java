package com.dfn.at.common.util;

import com.dfn.at.common.beans.ApplicationConfiguration;

public class ConfigurationReader {
    private static ApplicationConfiguration applicationConfiguration;

    public static void setApplicationConfiguration(ApplicationConfiguration applicationConfiguration) {
        ConfigurationReader.applicationConfiguration = applicationConfiguration;
    }

    public static ApplicationConfiguration getApplicationConfiguration() {
        return applicationConfiguration;
    }
}
