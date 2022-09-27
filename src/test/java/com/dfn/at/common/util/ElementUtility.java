package com.dfn.at.common.util;

import com.dfn.at.core.constants.EnvironmentConstants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementUtility {
    private static WebDriver getDriver() {
        return WebDriverManager.getDriver();
    }

    public static void clickWithWait(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), EnvironmentConstants.EXPLICIT_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(by));

        click(by);
    }

    public static void click(By locator) {
        getElement(locator).click();
    }

    public static void waitForElementToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), EnvironmentConstants.EXPLICIT_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void clickDropDown(By dropdownLocator, By dropdownItemLocator) {
        WebElement webElement = getParent(getParent(getElement(dropdownLocator)));

        webElement.click();
        getElement(dropdownItemLocator).click();
    }

    public static WebElement getParent(WebElement webElement) {
        WebElement parent = webElement.findElement(By.xpath("./.."));

        return parent;
    }

    public WebElement setTextWithWait(By by, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), EnvironmentConstants.EXPLICIT_WAIT);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.sendKeys(text);
        return element;
    }

    public static WebElement getElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        return element;
    }

    public static void setText(By locator, String value) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public static void updateText(By locator, String value) {
        WebElement element = getElement(locator);
        element.sendKeys(value);
    }

    public static void clearText(By locator, String value) {
        WebElement element = getElement(locator);
        element.clear();
    }

    public static String getText(By locator) {
        return getElement(locator).getText();
    }

    public static String getValue(By locator) {
        return getElement(locator).getAttribute("value");
    }

    public static String getInnerHtml(By locator) {
        return getElement(locator).getAttribute("innerHTML");
    }

    public static boolean isDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    public static boolean isEnabled(By locator) {
        return getElement(locator).isEnabled();
    }

    public static WebElement waitForElementToBeVisible(By locator) {
        return waitForElementToBeVisible(locator, EnvironmentConstants.EXPLICIT_WAIT);
    }

    public static WebElement waitForElementToBeVisible(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementPresent(By locator) {
        return waitForElementPresent(locator, EnvironmentConstants.EXPLICIT_WAIT);
    }

    public WebElement waitForElementPresent(By locator, long timeOut) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void selectDropDownItemByText(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(value);
    }

    public static void selectDropDownItemByIndex(By locator, int value) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(value);
    }

    public static void selectDropDownItemByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public static void dragAndDrop(WebDriver driver, By sourceElement, By destinationElement) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getElement(sourceElement), getElement(destinationElement)).pause(Duration.ofSeconds(2)).release().build().perform();
    }

    public static void rightClick(WebDriver driver, By locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(getElement(locator)).build().perform();
    }

    public static void rightCLickWithWait(WebDriver driver, By locator) {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, EnvironmentConstants.EXPLICIT_WAIT);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        actions.contextClick(element).build().perform();
    }

    public static void doubleClick(WebDriver driver, By locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(getElement(locator)).build().perform();
    }

    public static String switchToWindowAndGetTitle(String windowId) {
        getDriver().switchTo().window(windowId);
        String title = getDriver().getTitle();
        return title;
    }

    public static Alert waitForAlertPresent(int timeOut) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOut);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public static String getAlertText(int timeOut) {
        return waitForAlertPresent(timeOut).getText();
    }

    public static void acceptAlert(int timeOut) {
        waitForAlertPresent(timeOut).accept();
    }

    public static void dismissAlert(int timeOut) {
        waitForAlertPresent(timeOut).dismiss();
    }
}
