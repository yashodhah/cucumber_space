package com.dfn.at.core.pages;

import com.dfn.at.common.util.ElementUtility;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    By txtUsername = By.xpath("//*[@id='username']");
    By txtPassword = By.xpath("//*[@id='password']");
    By btnLogin = By.xpath("//*[@id='app-login-button']");

    public void enterUsername(String username) {
        ElementUtility.setText(txtUsername, username);
    }

    public void enterPassword(String password) {
        ElementUtility.setText(txtPassword, password);
    }

    public void clickLoginButton() {
        ElementUtility.click(btnLogin);
    }

    public HomePage goToHomePage() {
        return new HomePage();
    }
}
