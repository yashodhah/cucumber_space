package com.dfn.at.core.step_definitions;

import com.dfn.at.core.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class LoginSteps extends BaseStep {
    LoginPage loginPage = new LoginPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        Assert.assertTrue(true);
    }

    @When("user enters username and password")
    public void user_enters_username_and_password() {
        loginPage.enterUsername("kasun");
        loginPage.enterPassword("123");
    }

    @When("user enters {string} in login name text box")
    public void user_enters_username(String string) {
        loginPage.enterUsername(string);
    }

    @When("user enters {string} in password text box")
    public void user_enters_password(String string) {
        loginPage.enterPassword(string);
    }

    @And("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLoginButton();
    }

    @And("I log in as standard user")
    public void login_as_valid_user() throws IOException {
        loginPage.enterUsername("dy");
        loginPage.enterPassword("123");
        loginPage.clickLoginButton();

//        Assert.assertTrue(loginPagePO.goToHomePage().isVerticalMenuDisplayed());
        Assert.assertEquals(loginPage.goToHomePage().getPageHeader(), "10.010.0.0");
    }

    @Then("user is navigated to home page")
    public void user_is_navigated_to_home_page() {
        Assert.assertEquals(loginPage.goToHomePage().getPageHeader(), "10.010.0.0");
    }
}
