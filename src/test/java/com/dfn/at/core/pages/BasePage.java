package com.dfn.at.core.pages;

import com.dfn.at.common.beans.StatusHistoryRequest;
import com.dfn.at.common.beans.TestCategory;
import com.dfn.at.common.services.DBVerificationService;
import com.dfn.at.common.util.ElementUtility;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class BasePage {
    private DBVerificationService dbVerificationService;
    private Map<String, Object> dbFieldValueMap;

    public BasePage() {
        this.dbFieldValueMap = new HashMap<>();
    }

    @Autowired
    public void setDbVerificationService(DBVerificationService dbVerificationService) {
        this.dbVerificationService = dbVerificationService;
    }

    public void confirmYes() {
        By confirmationOkBtn = By.name("msg-box-btn-ok");

        ElementUtility.waitForElementToBeVisible(confirmationOkBtn);
        ElementUtility.getElement(confirmationOkBtn).click();
    }

    public String getNotificationSummary() {
        By notificationSummary = By.xpath("//p-toastitem/div/div/div/div");

        ElementUtility.waitForElementToBeVisible(notificationSummary, 20);
        return ElementUtility.getText(notificationSummary).toLowerCase();
    }

    public void closeNotification() {
        By notificationClose = By.xpath("//p-toastitem/div/div/a");
        ElementUtility.clickWithWait(notificationClose);
    }

    public void verifyDBData(TestCategory testCategory, String tableName, String description) {
        this.dbVerificationService.verifyDBData(testCategory, dbFieldValueMap, tableName, description);
        this.dbFieldValueMap.clear();
    }

    public void verifyStatusHistoryCount(StatusHistoryRequest statusHistoryRequest) {
        this.dbVerificationService.verifyStatusHistoryCount(statusHistoryRequest);
    }

    public void setText(By locator, String value) {
        ElementUtility.setText(locator, value);
    }

    public void setText(By locator, String value, String dbField) {
        ElementUtility.setText(locator, value);
        updateDBFieldValueMap(dbField, value);
    }

    public void selectDropdownValue(By dropdown, By dropdownItem) {
        ElementUtility.clickDropDown(dropdown, dropdownItem);
    }

    public void selectDropdownValue(By dropdown, By dropdownItem, String dbField, String value) {
        selectDropdownValue(dropdown, dropdownItem);
        updateDBFieldValueMap(dbField, value);
    }

    public void updateDBFieldValueMap(String field, String value) {
        if (value != null && !value.isEmpty()) {
            this.dbFieldValueMap.put(field, value);
        }
    }

    public Object getValueFromDBFieldValueMap(String key) {
        return dbFieldValueMap.get(key);
    }
}
