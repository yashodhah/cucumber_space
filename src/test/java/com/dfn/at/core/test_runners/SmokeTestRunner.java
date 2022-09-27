package com.dfn.at.core.test_runners;

import com.dfn.at.core.constants.EnvironmentConstants;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/smoke.cucumber.json"},
        features = {"src/test/resources/core/features"},
        glue = {"com/dfn/at/core/step_definitions"},
        tags = "@Smoke"
)
public class SmokeTestRunner extends TestNGTestRunner {
    public SmokeTestRunner() {
        super(EnvironmentConstants.USER_1, EnvironmentConstants.PASSWORD_1);
    }
}
