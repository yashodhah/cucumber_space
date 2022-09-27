package com.dfn.at.core.pages.common;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.pages.BasePage;
import org.openqa.selenium.By;

public class DataViewGridPage extends BasePage {
    By gridAddBtn = By.name("grd-btn-add");
    By gridEditBtn = By.name("grd-btn-edit");
    By gridApproveBtn = By.name("grd-approve-btn-Approve");

    public void clickGridAddButton() {
        ElementUtility.getElement(gridAddBtn).click();
    }

    public void clickGridEditButton() {
        ElementUtility.waitForElementToBeVisible(gridEditBtn);
        ElementUtility.getElement(gridEditBtn).click();
    }

    public void clickGridApproveButton() {
        ElementUtility.getElement(gridApproveBtn).click();
        this.confirmYes();
    }
}
