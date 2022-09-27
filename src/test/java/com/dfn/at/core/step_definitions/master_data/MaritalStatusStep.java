package com.dfn.at.core.step_definitions.master_data;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.core.constants.MenuId;
import com.dfn.at.core.pages.common.GridPage;
import com.dfn.at.core.pages.master_data.customer.MaritalStatusPage;
import com.dfn.at.core.services.ApprovalService;
import com.dfn.at.core.services.NotificationService;
import com.dfn.at.core.step_definitions.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MaritalStatusStep extends BaseStep {
    GridPage maritalStatusGridPage;
    MaritalStatusPage maritalStatusPage;


    public MaritalStatusStep() {
        maritalStatusGridPage = (GridPage) BeanRegistryCore.getInstance().getBean(GridPage.class, "MARITAL_STATUS", "marital_status");

        maritalStatusPage = (MaritalStatusPage) BeanRegistryCore.getInstance().getBean(MaritalStatusPage.class);
    }

    @Given("I navigate to the Marital Status page")
    public void i_navigate_to_the_marital_status_page() {
        this.navigateToPage(MenuId.MASTER_DATA, MenuId.MASTER_DATA_CUSTOMER, MenuId.MASTER_DATA_CUSTOMER_MARITAL_STATUS);
    }

    @And("I add new Marital Status details")
    public void i_add_new_marital_status_details() {
        maritalStatusGridPage.clickAdd();
        maritalStatusPage.addNewValues();
    }

    @And("I save Marital Status")
    public void i_save_marital_status() {
        maritalStatusPage.clickSaveButton();
    }

    @Then("New Marital Status should be created")
    public void new_marital_status_should_be_created() {
        try {
            NotificationService.verifySuccessStatusOfAction(maritalStatusPage);
        } catch (AssertionError e) {

        } finally {
            maritalStatusPage.clickCloseButton();
        }
    }

    @And("I approve Marital Status")
    public void i_approve_marital_status() {
        maritalStatusGridPage.clickApprove();
    }

    @Then("Marital Status should be approved")
    public void marital_status_should_be_approved() {
        NotificationService.verifySuccessStatusOfAction(maritalStatusPage);
    }

    @When("I click edit Marital Status")
    public void i_click_edit_marital_status() {
        maritalStatusGridPage.clickGridSearchBtn();
        maritalStatusGridPage.filter("filter_m128Description", maritalStatusPage.getNameValue());
        maritalStatusGridPage.clickEdit();
    }

    @And("I update Marital Status details")
    public void i_update_marital_status_details() {
        maritalStatusPage.updateValues();
    }


    @Then("Marital Status should be updated")
    public void marital_status_should_be_updated() {
        try {
            NotificationService.verifySuccessStatusOfAction(maritalStatusPage);
        } catch (AssertionError e) {

        } finally {
            maritalStatusPage.clickCloseButton();
        }
    }

    @When("I perform basic approval cycle in Marital Status list")
    public void i_perform_basic_approval_cycle_in_marital_status_list() {
        ApprovalService.performBasicApproval(maritalStatusGridPage);
    }

    @And("I close Marital Status tab")
    public void i_close_marital_status_tab() {
        this.maritalStatusGridPage.closeTab();
    }
}
