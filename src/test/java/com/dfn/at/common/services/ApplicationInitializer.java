package com.dfn.at.common.services;

import com.dfn.at.common.beans.ApplicationConfiguration;
import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.common.beans.BeanRegistryI;
import com.dfn.at.common.util.ConfigurationReader;
import com.dfn.at.common.util.TestUtility;
import com.dfn.at.core.constants.EnvironmentConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ApplicationInitializer {
    private static boolean isExecuted = false;

    public static void initialize() throws Exception {
        try {
            processOneTimeMethods();
        } catch (Exception e) {
            throw e;
        }
    }

    private static synchronized void processOneTimeMethods() throws Exception {
        if (!isExecuted) {
            loadApplicationConfigurations();
            initializeDbConnection();
            initSpringContext();
            loadAppSettings();
            clearResources();
            generateDirectory();

            isExecuted = true;
        }
    }

    private static void clearResources() {
        TestUtility.clearResources();
    }

    private static boolean generateDirectory() {
        File dir = new File("target/delivery");
        return dir.mkdir();
    }

    private static void loadApplicationConfigurations() throws IOException {
        loadEnvironmentConfigurations();
    }

    private static void loadAppSettings() {
        SettingsHandlerServiceI settingsHandlerServiceI = (SettingsHandlerServiceI) BeanRegistryCore.getInstance().getBean(SettingsHandlerServiceI.class);
        settingsHandlerServiceI.loadSettings();
    }

    private static void loadEnvironmentConfigurations() throws IOException {
        InputStream inJson = Files.newInputStream(Paths.get("env-config.json"));
        ApplicationConfiguration config = new ObjectMapper().readValue(inJson, ApplicationConfiguration.class);

        ConfigurationReader.setApplicationConfiguration(config);

        EnvironmentConstants.URL = config.getUrl();
        EnvironmentConstants.BROWSER = config.getBrowser();
        EnvironmentConstants.HEADLESS = config.isHeadless();
    }

    // TODO: [YD] Read maven properties
    private static void readMavenProperties() throws IOException {
        try {
            InputStream is = ApplicationInitializer.class.getResourceAsStream("my.properties");
            Properties p = new Properties();
            p.load(is);

            String name = p.getProperty("test_mode");

        } catch (Exception e) {

        }
    }

    private static void initializeDbConnection() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    public static void initSpringContext() {
        ClassPathXmlApplicationContext springContext = SpringContextHandler.init(EnvironmentConstants.SPRING_FILE_PATH);

        BeanRegistryI beanRegistryI = BeanRegistryCore.getInstance();
        beanRegistryI.init(springContext);
    }
}
