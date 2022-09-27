package com.dfn.at.common.util;

import com.dfn.at.core.constants.ApplicationConstants;
import com.dfn.at.core.constants.EnvironmentConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestUtility {
    //Explicit Wait to Click on WebElement.
    public static void clickOn(WebDriver driver, WebElement element, int timeout) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    //Explicit Wait to Send Data to WebElement.
    public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) {
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    //Explicit Wait for Element To Be Visible.
    public static void waitForElementToBeVisible(WebDriver driver, By locator, int timeout) {
        new WebDriverWait(driver, timeout).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void isElementDisplayed(WebElement element) {
        boolean elementDisplayed = element.isDisplayed();
        if (elementDisplayed) {
            System.out.println("Element is Displayed");
        } else {
            System.out.println("Element is not Displayed");
        }
    }

    //To Check Element is Enabled or No.
    public static void isElementEnabled(WebElement element) {
        boolean elementEnabled = element.isEnabled();
        if (elementEnabled) {
            System.out.println("Element is Enabled");
        } else {
            System.out.println("Element is not Enabled");
        }
    }

    //To Select a value from Drop Down by using SelectByVisibleText Method.
    public static void selectValueFromDropDownByText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    //To Select a value from Drop Down by using SelectByIndex Method.
    public static void selectValueFromDropDownByIndex(WebElement element, int value) {
        Select select = new Select(element);
        select.selectByIndex(value);
    }

    //To Select a value from Drop Down by using SelectByValue Method.
    public static void selectValueFromDropDownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    //To Click on Element using Actions Class.
    public void clickOnElementUsingActions(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    //To Mouse Hover and Click or Select an Element using Actions Class.
    public static void moveToElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    //To Perform Drag and Drop action using Actions Class - 1.
    public static void dragAndDrop_1(WebDriver driver, WebElement sourceElement, WebElement destinationElement) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
    }

    //To Perform Drag and Drop action using Actions Class - 2.
    public static void dragAndDrop_2(WebDriver driver, WebElement sourceElement, WebElement destinationElement) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(sourceElement).pause(Duration.ofSeconds(2)).moveToElement(destinationElement).pause(Duration.ofSeconds(2)).release().build().perform();
    }

    //To Perform Right Click action using Actions Class.
    public static void rightClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).build().perform();
    }

    //To perform Double Click action using Actions Class.
    public static void doubleClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.doubleClick(element).build().perform();
    }

    //To Accept Alert Pop-Up.
    public static void acceptAlertPopup(WebDriver driver) throws InterruptedException {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            Thread.sleep(2000);
            alert.accept();
            System.out.println("Alert Accepted Successfully");
        } catch (Exception e) {
            System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
        }
    }

    //To Dismiss Alert Pop-Up.
    public static void dismissAlertPopup(WebDriver driver) throws InterruptedException {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            Thread.sleep(2000);
            alert.dismiss();
            System.out.println("Alert Dismissed Successfully");
        } catch (Exception e) {
            System.out.println("Something Went Wrong ==>> Please Check ::: " + e.getMessage());
        }
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = EnvironmentConstants.SCREENSHOTS_PATH + "/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        return destination;
    }

    public static String getCreateValue() {
        return ApplicationConstants.CREATE_VALUE;
    }

    public static String getCreateValue(String value) {
        return ApplicationConstants.CREATE_VALUE + "_" + value;
    }

    public static String getUpdateValue() {
        return ApplicationConstants.UPDATE_VALUE;
    }

    public static String getUpdateValue(String value) {
        return ApplicationConstants.UPDATE_VALUE + "_" + value;
    }

    public static String getCreateValueShort() {
        return ApplicationConstants.CREATE_VALUE_S;
    }

    public static String getCreateValueShort(String value) {
        return ApplicationConstants.CREATE_VALUE_S + "_" + value;
    }

    public static String getUpdateValueShort() {
        return ApplicationConstants.UPDATE_VALUE_S;
    }

    public static String getUpdateValueShort(String value) {
        return ApplicationConstants.UPDATE_VALUE_S + "_" + value;
    }

    public static void clearResources() {
        FileHandler.deleteDirectory(EnvironmentConstants.SCREENSHOTS_PATH);
        FileHandler.deleteDirectory(EnvironmentConstants.CUCUMBER_REPORT_PATH);
        FileHandler.deleteDirectory(EnvironmentConstants.REPORT_MERGE_PATH);
        FileHandler.deleteDirectory(EnvironmentConstants.REPORT_ZIP_DELIVERY_PATH);
    }

    /**
     * Manually freezing the current operation.
     * Do not use this in any case when you don't know the upper or lower bound of the waiting time
     *
     * @param seconds
     */
    public static void manualWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}