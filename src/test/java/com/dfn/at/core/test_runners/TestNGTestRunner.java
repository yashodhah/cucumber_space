package com.dfn.at.core.test_runners;

import com.dfn.at.common.services.ApplicationInitializer;
import com.dfn.at.common.services.LogHandler;
import com.dfn.at.common.services.LogHandlerI;
import com.dfn.at.common.util.TestEventListener;
import com.dfn.at.common.util.TestUtility;
import com.dfn.at.common.util.WebDriverManager;
import com.dfn.at.core.constants.EnvironmentConstants;
import com.dfn.at.core.pages.LoginPage;
import com.dfn.at.core.test_runners.tags.ExcludedTags;
import com.dfn.at.core.test_runners.tags.IncludedTags;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Listeners(TestEventListener.class)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    LogHandlerI logger = new LogHandler(TestNGTestRunner.class);

    protected TestNGCucumberRunner testNGCucumberRunner;

    private String userName;
    private String password;

    public TestNGTestRunner(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {

        try {
            logger.logInfo("Initializing Automation");

            ApplicationInitializer.initialize();
            WebDriverManager.initializeDriver();

            initAutomation();
            loginToSystem();
            TestUtility.manualWait(EnvironmentConstants.AT_INIT_WAIT);
        } catch (Exception e) {
            logger.logError("Error in initializing Automation", e);
        }
    }

    @Test(groups = "Cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }

        return getFilteredScenarios();
    }

    public Object[][] getFilteredScenarios() {
        Object[][] includedTestObjects = filterTestsByIncludeTags(testNGCucumberRunner.provideScenarios(), getIncludedTags());
        Object[][] filteredTestObjects = filterTestsByExcludeTags(includedTestObjects, getExcludedTags());

        return filteredTestObjects;
    }

    // TODO: [YD] Remove memory usage by in-place replacing
    private Object[][] filterTestsByIncludeTags(Object[][] scenarioObjectList, List<List<String>> includedTags) {
        if (includedTags.size() == 0) {
            return scenarioObjectList;
        }

        List<Object[]> filteredList = new ArrayList<>();

        for (Object[] scenarioObject : scenarioObjectList) {
            List tags = (((PickleWrapper) scenarioObject[0]).getPickle()).getTags();

            for (int i = 0; i < includedTags.size(); i++) {
                if (tags.containsAll(includedTags.get(i))) {
                    filteredList.add(scenarioObject);
                    break;
                }
            }
        }

        return filteredList.toArray(new Object[0][]);
    }

    private Object[][] filterTestsByExcludeTags(Object[][] scenarioObjectList, List<List<String>> excludedTags) {
        if (excludedTags.size() == 0) {
            return scenarioObjectList;
        }

        List<Object[]> filteredList = new ArrayList<>();

        for (Object[] scenarioObject : scenarioObjectList) {
            boolean isExcluded = false;
            List tags = (((PickleWrapper) scenarioObject[0]).getPickle()).getTags();

            for (int i = 0; i < excludedTags.size(); i++) {
                if (tags.containsAll(excludedTags.get(i))) {
                    isExcluded = true;
                    break;
                }
            }

            if (!isExcluded) {
                filteredList.add(scenarioObject);
            }
        }

        if (filteredList.size() == 0) {
            return scenarioObjectList;
        }

        return filteredList.toArray(new Object[0][]);
    }


    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        try {
            testNGCucumberRunner.finish();
            WebDriverManager.closeInstance();
        } catch (Exception e) {
        }
    }

    public void initAutomation() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

        setIncludedTests();
        setExcludedTests();
    }

    public void setExcludedTests() {
        ExcludedTags.initExcludedTags();
    }

    public void setIncludedTests() {
        IncludedTags.initIncludedTags();
    }

    public List<List<String>> getExcludedTags() {
        return ExcludedTags.getExcludedTags();
    }

    public List<List<String>> getIncludedTags() {
        return IncludedTags.getIncludedTags();
    }

    private void loginToSystem() {
        LoginPage loginPage = new LoginPage();

        loginPage.enterUsername(this.userName);
        loginPage.enterPassword(this.password);
        loginPage.clickLoginButton();
    }
}
