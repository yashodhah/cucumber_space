package com.dfn.at.core.pages.common;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.pages.BasePage;
import org.openqa.selenium.By;

public class TabPage extends BasePage {
    String tabId;
    private By tabCloseBtn;
    private By tabHeader;

    public TabPage(String tabId) {
        setTabElements(tabId);
    }

    private void setTabElements(String tabId) {
        this.tabId = tabId;

        tabHeader = By.id(tabId);
        tabCloseBtn = By.id(tabId + "_close");
    }

    public void closeTab() {
        tabCloseBtn = By.id(tabId + "_close");

        ElementUtility.clickWithWait(tabCloseBtn);
    }

    public void clickTab() {
        ElementUtility.clickWithWait(tabHeader);
    }

    public String getPageTitle() {
        return ElementUtility.getText(tabHeader);
    }
}
