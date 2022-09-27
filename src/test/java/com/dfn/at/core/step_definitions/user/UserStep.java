package com.dfn.at.core.step_definitions.user;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.core.constants.MenuId;
import com.dfn.at.core.pages.common.GridPage;
import com.dfn.at.core.pages.user.UserAuthenticationPage;
import com.dfn.at.core.pages.user.UserPage;
import com.dfn.at.core.services.NotificationService;
import com.dfn.at.core.step_definitions.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserStep extends BaseStep {
    GridPage userGridPage = (GridPage) BeanRegistryCore.getInstance().getBean(GridPage.class, "EMPLOYEE_LIST", "users");
    UserPage userPage = (UserPage) BeanRegistryCore.getInstance().getBean(UserPage.class);
    UserAuthenticationPage userAuthenticationPage = (UserAuthenticationPage) BeanRegistryCore.getInstance().getBean(UserAuthenticationPage.class);

    @Given("I navigate to the employees page")
    public void i_navigate_to_the_employees_page() {
        this.navigateToPage(MenuId.USERS, MenuId.USERS_USER);
        userGridPage.clickAdd();
    }

    @When("I add new employee details")
    public void i_add_new_employee() {
        userPage.addNewValues();
    }

    @When("I press next button on employees page")
    public void i_press_next_button_on_employees_page() {
        userPage.clickSaveButton();
    }

    @And("I add new employee authentication details")
    public void i_add_new_employee_authentication_details() {
        userAuthenticationPage.addNewValues();
    }

    @And("I press save button on employee authentication page")
    public void i_press_save_button_on_employee_authentication_page() {
        userAuthenticationPage.clickSaveButton();
        this.userAuthenticationPage.confirmYes();
    }

    @Then("New employee should be created")
    public void new_employee_should_be_created() {
        NotificationService.verifySuccessStatusOfAction(userPage);
        this.userAuthenticationPage.clickCloseBtn();
    }

    @Then("I approve employee")
    public void i_approve_employee() {
        this.userGridPage.clickApprove();
    }

    @Then("Employee should be approved")
    public void employee_should_be_approved() {
        NotificationService.verifySuccessStatusOfAction(userPage);
    }

    @When("I click edit employee")
    public void i_click_edit_employee() {

    }

    @When("I edit the employee")
    public void i_edit_the_employee() {

    }

    @Then("Employee should be updated")
    public void employee_should_be_updated() {

    }
}
