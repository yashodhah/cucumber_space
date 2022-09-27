package com.dfn.at.common.util;

import com.dfn.at.common.services.LogHandler;
import com.dfn.at.common.services.TestLinkLogHandler;
import com.dfn.at.core.constants.EnvironmentConstants;
import com.dfn.at.core.constants.TestLinkMapping;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    static LogHandler logger = new LogHandler(WebDriverManager.class.getName());

    private final static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void closeInstance() {
        tlDriver.get().quit();
    }

    public synchronized static WebDriver initializeDriver() {
        logger.logInfo("Initializing web driver");

        WebDriver webDriver = null;
        final String browserName = EnvironmentConstants.BROWSER;
        setUpWebDriverBasedOnOS();

        if (browserName.equals("Chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();

            if (EnvironmentConstants.HEADLESS) {
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1880,1880");
            }

            webDriver = new ChromeDriver(chromeOptions);
        } else if (browserName.equals("IE")) {
            webDriver = new InternetExplorerDriver();
        } else if (browserName.equals("Firefox")) {
            webDriver = new FirefoxDriver();
        } else if (browserName.equals("HtmlUnit")) {
            webDriver = new HtmlUnitDriver();
        } else {
            logger.logError("Path of Driver Executable is not Set for any Browser");
        }

        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().pageLoadTimeout(EnvironmentConstants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(EnvironmentConstants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        webDriver.get(EnvironmentConstants.URL);

        tlDriver.set(webDriver);

        return getDriver();
    }

    private static void setUpWebDriverBasedOnOS() {
        if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_MAC) {
            System.setProperty("webdriver.chrome.driver", "./web_drivers/linux/chromedriver");
            System.setProperty("webdriver.ie.driver", "./web_drivers/linux/iedriverserver");
            System.setProperty("webdriver.gecko.driver", "./web_drivers/linux/geckodriver");
            System.setProperty("phantomjs.binary.path", "./web_drivers/linux/phantomjs");
        }

        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver", "./web_drivers/windows/chromedriver.exe");
            System.setProperty("webdriver.ie.driver", "./web_drivers/windows/iedriverserver.exe");
            System.setProperty("webdriver.gecko.driver", "./web_drivers/windows/geckodriver.exe");
            System.setProperty("phantomjs.binary.path", "./web_drivers/windows/phantomjs.exe");
        }
    }
}
