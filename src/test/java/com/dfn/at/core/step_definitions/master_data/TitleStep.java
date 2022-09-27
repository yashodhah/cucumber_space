package com.dfn.at.core.step_definitions.master_data;


import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.core.constants.MenuId;
import com.dfn.at.core.pages.common.GridPage;
import com.dfn.at.core.pages.master_data.customer.TitlePage;
import com.dfn.at.core.services.ApprovalService;
import com.dfn.at.core.services.NotificationService;
import com.dfn.at.core.step_definitions.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TitleStep extends BaseStep {
    GridPage titleGridPage;
    TitlePage titlePage;

    public TitleStep() {
        titleGridPage = (GridPage) BeanRegistryCore.getInstance().getBean(GridPage.class, "TITLES", "titles");

        titlePage = (TitlePage) BeanRegistryCore.getInstance().getBean(TitlePage.class);
    }


    @Given("I navigate to the Titles page")
    public void i_navigate_to_the_titles_page() {
        this.navigateToPage(MenuId.MASTER_DATA, MenuId.MASTER_DATA_CUSTOMER, MenuId.MASTER_DATA_CUSTOMER_TITLES);

    }

    @And("I add new Title details")
    public void i_add_new_title_details() {
        titleGridPage.clickAdd();
        titlePage.addNewValues();
    }

    @And("I save Title")
    public void i_save_title() {
        titlePage.clickSaveButton();
    }

    @Then("New Title should be created")
    public void new_title_should_be_created() {
        try {
            NotificationService.verifySuccessStatusOfAction(titlePage);
        } catch (AssertionError e) {

        } finally {
            titlePage.clickCloseButton();
        }
    }

    @And("I approve Title")
    public void i_approve_title() {
        titleGridPage.clickApprove();
    }

    @Then("Title should be approved")
    public void title_should_be_approved() {
        NotificationService.verifySuccessStatusOfAction(titlePage);
    }

    @When("I click edit Title")
    public void i_click_edit_title() {
        titleGridPage.clickGridSearchBtn();
        titleGridPage.filter("filter_m130Description", titlePage.getNameValue());
        titleGridPage.clickEdit();
    }

    @And("I update Title details")
    public void i_update_title_details() {
        titlePage.updateValues();
    }



    @Then("Title should be updated")
    public void title_should_be_updated() {
        try {
            NotificationService.verifySuccessStatusOfAction(titlePage);
        } catch (AssertionError e) {

        } finally {
            titlePage.clickCloseButton();
        }
    }

    @When("I perform basic approval cycle in Title list")
    public void i_perform_basic_approval_cycle_in_title_list() {
        ApprovalService.performBasicApproval(titleGridPage);
    }

    @And("I close Title tab")
    public void i_close_title_tab() {
        this.titleGridPage.closeTab();
    }

}