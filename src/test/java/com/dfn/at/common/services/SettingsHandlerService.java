package com.dfn.at.common.services;

import com.dfn.at.common.beans.ApplicationConfiguration;
import com.dfn.at.common.beans.Context;
import com.dfn.at.common.util.ConfigurationReader;
import com.dfn.at.common.util.ScenarioContextMap;
import com.dfn.at.core.constants.ApplicationConstants;
import com.dfn.at.core.constants.EnvironmentConstants;

public class SettingsHandlerService implements SettingsHandlerServiceI {
    public void loadSettings() {
        loadApplicationConstants();
        loadDefaultContextDetails();
    }

    public void loadDefaultContextDetails() {
        ScenarioContextMap.setContext(Context.CUSTOMER_ID, ApplicationConstants.DEFAULT_CUSTOMER_ID);
    }

    public void loadApplicationConstants() {
        ApplicationConfiguration applicationConfiguration = ConfigurationReader.getApplicationConfiguration();

        EnvironmentConstants.URL = applicationConfiguration.getUrl();
        EnvironmentConstants.BROWSER = applicationConfiguration.getBrowser();

//        ApplicationConstants.DEFAULT_CUSTOMER_ID = applicationConfiguration
    }
}
