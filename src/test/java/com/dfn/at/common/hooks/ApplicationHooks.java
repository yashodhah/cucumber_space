package com.dfn.at.common.hooks;

import com.dfn.at.common.services.ApplicationInitializer;
import com.dfn.at.common.util.TestUtility;
import com.dfn.at.common.util.WebDriverManager;
import com.dfn.at.core.constants.EnvironmentConstants;
import com.dfn.at.core.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
    static boolean isInitialized = false;
    static boolean isLoggedIn = false;

    @Before(order = 0)
    public void init() {
        if (!isInitialized) {
            try {
                ApplicationInitializer.initialize();
                WebDriverManager.initializeDriver();

                isInitialized = true;
            } catch (Exception e) {

            }
        }
    }

    @Before(order = 1)
    public void loginToSystem() {
        if (!isLoggedIn) {
            LoginPage loginPage = new LoginPage();

            loginPage.enterUsername(EnvironmentConstants.USER_1);
            loginPage.enterPassword(EnvironmentConstants.PASSWORD_1);
            loginPage.clickLoginButton();

            isLoggedIn = true;

            TestUtility.manualWait(EnvironmentConstants.AT_INIT_WAIT);
        }
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                TestUtility.getScreenshot(WebDriverManager.getDriver(), screenshotName);
            } catch (Exception ex) {

            }
        }
    }
}
