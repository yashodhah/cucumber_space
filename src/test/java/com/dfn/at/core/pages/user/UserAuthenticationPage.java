package com.dfn.at.core.pages.user;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.pages.common.DialogPage;
import org.openqa.selenium.By;

public class UserAuthenticationPage extends DialogPage {
    By loginName = By.xpath("//dfnat-uppercase-input-block[@name='u17LoginName']//input");
    By password = By.xpath("//*[@id='u17Password']");

    By saveButton = By.name("emp-auth-save-btn");
    By closeButton = By.name("emp-auth-close-btn");

    public void addNewValues() {
        ElementUtility.setText(loginName, "AUTOMATION USER");
        ElementUtility.setText(password, "123");
    }

    public void clickSaveButton() {
        ElementUtility.click(saveButton);
    }

    public void clickCloseBtn() {
        ElementUtility.click(closeButton);
    }

}
