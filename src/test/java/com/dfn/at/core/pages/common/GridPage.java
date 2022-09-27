package com.dfn.at.core.pages.common;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.common.util.WebDriverManager;
import org.openqa.selenium.By;

public class GridPage extends TabPage {
    By gridAddBtn = By.name("grid-add-button");
    By gridSearchBtn = By.name("grid-search-button");
    By gridRefreshBtn = By.name("grid-refresh-button");

    By filterField;
    By gridFirstRow;
    By gridFirstRowStatus;

    public GridPage(String gridName, String tabId) {
        super(tabId);
        setGridFirstRow(gridName);
    }

    /**
     * Return the first value with element name td_{{columnName}}
     *
     * @param columnName
     * @return
     */
    public String getFirstGridValue(String columnName) {
        By gridId = By.name("td_" + columnName);
        return ElementUtility.getInnerHtml(gridId);
    }

    public void clickAdd() {
        // TODO:[YD] Wait until columns and data load
        ElementUtility.waitForElementToBeVisible(gridFirstRow);
        ElementUtility.click(gridAddBtn);
    }

    public void clickGridSearchBtn() {
        ElementUtility.click(gridSearchBtn);
    }

    public void clickEdit() {
        By editCM = By.xpath("//*[@id='Edit']");
        _clickOnContextMenu(editCM);
    }

    public void clickApprove() {
        By approveCM = By.xpath("//*[@id='Approve']");
        _clickOnContextMenu(approveCM);
        this.confirmYes();
    }

    public void clickMarkForDelete() {
        By approveCM = By.xpath("//*[@id='Mark_for_Delete']");
        _clickOnContextMenu(approveCM);
        this.confirmYes();
    }

    public void clickRestore() {
        By approveCM = By.xpath("//*[@id='Restore']");
        _clickOnContextMenu(approveCM);
        this.confirmYes();
    }

    public void clickDelete() {
        By approveCM = By.xpath("//*[@id='Delete']");
        _clickOnContextMenu(approveCM);
        this.confirmYes();
    }

    public void clickGrant() {
        By approveCM = By.xpath("//*[@id='Grant']");
        _clickOnContextMenu(approveCM);
        this.confirmYes();
    }

    public void clickReject() {
        By approveCM = By.xpath("//*[@id='Reject']");
        _clickOnContextMenu(approveCM);
        this.confirmYes();
    }

    private void rightClickOnGridItem() {
        ElementUtility.rightCLickWithWait(WebDriverManager.getDriver(), gridFirstRow);
    }

    private void _clickOnContextMenu(By editCM) {
        rightClickOnGridItem();
        ElementUtility.clickWithWait(editCM);
    }

    public void filter(String filterFieldId, String filterValue) {
        this.filterField = By.id(filterFieldId);

        ElementUtility.click(filterField);
        ElementUtility.setText(filterField, filterValue);
        ElementUtility.click(gridRefreshBtn);
        ElementUtility.waitForElementToBeClickable(gridRefreshBtn);
    }

    private void setGridFirstRow(String gridName) {
        String xpath = "//dfnat-grid[@name='" + gridName + "']//tbody//tr";

        gridFirstRow = By.xpath(xpath);
        gridFirstRowStatus = By.xpath(xpath + "//span[@name='statusDescription']");
    }
}
