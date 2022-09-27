package com.dfn.at.core.test_runners.child_runners;

import com.dfn.at.core.constants.EnvironmentConstants;
import com.dfn.at.core.test_runners.TestNGTestRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/users.cucumber.json"},
        features = {"src/test/resources/core/features/user"},
        glue = {"com/dfn/at/core/step_definitions/common",
                "com/dfn/at/core/step_definitions/user"}
)
public class UsersTestRunner extends TestNGTestRunner {
    public UsersTestRunner() {
        super(EnvironmentConstants.USER_2, EnvironmentConstants.PASSWORD_2);
    }
}
