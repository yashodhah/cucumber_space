package com.dfn.at.core.test_runners.child_runners;

import com.dfn.at.core.constants.EnvironmentConstants;
import com.dfn.at.core.test_runners.TestNGTestRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/master-data.cucumber.json"},
        features = {"src/test/resources/core/features/master_data"},
        glue = {"com/dfn/at/core/step_definitions/common",
                "com/dfn/at/core/step_definitions/master_data"}
)
public class MasterDataTestRunner extends TestNGTestRunner {
    public MasterDataTestRunner() {
        super(EnvironmentConstants.USER_1, EnvironmentConstants.PASSWORD_1);
    }
}