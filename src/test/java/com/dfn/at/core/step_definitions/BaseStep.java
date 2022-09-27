package com.dfn.at.core.step_definitions;

import com.dfn.at.common.util.ElementUtility;
import org.openqa.selenium.By;

public class BaseStep {
    public void navigateToPage(String... menuItemIds) {
        for (String menuId : menuItemIds) {
            By menuItem = By.id(menuId);
            ElementUtility.clickWithWait(menuItem);
        }
    }
}
