package com.dfn.at.core.pages;

import com.dfn.at.common.util.ElementUtility;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private By pageHeader = By.xpath("//body/dfnat-root/div/dfnat-home/div/p-toolbar/div/span[2][@class='version']");
    private By toolBar = By.className("vertical-menu");

    public String getPageHeader() {
        return ElementUtility.getText(pageHeader);
    }

    public boolean isVerticalMenuDisplayed() {
        return ElementUtility.isDisplayed(toolBar);
    }
}
