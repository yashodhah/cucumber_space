package com.dfn.at.core.step_definitions.master_data;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.common.services.TestLinkLogHandler;
import com.dfn.at.core.constants.MenuId;
import com.dfn.at.core.constants.TestLinkMapping;
import com.dfn.at.core.pages.common.GridPage;
import com.dfn.at.core.pages.master_data.country.CountryPage;
import com.dfn.at.core.services.ApprovalService;
import com.dfn.at.core.services.NotificationService;
import com.dfn.at.core.step_definitions.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CountryStep extends BaseStep {
    private static boolean isNavigated = false;

    GridPage countryGridPage;
    CountryPage countryPage;

    public CountryStep() {
        countryGridPage = (GridPage) BeanRegistryCore.getInstance().getBean(GridPage.class, "COUNTRY_LIST", "country");
        countryPage = (CountryPage) BeanRegistryCore.getInstance().getBean(CountryPage.class);
    }

    @Given("I navigate to the countries page")
    public void i_navigate_to_the_countries_page() {
        if (!isNavigated) {
            isNavigated = true;

            this.navigateToPage(MenuId.MASTER_DATA, MenuId.MASTER_DATA_COUNTRIES);
            Assert.assertEquals(countryGridPage.getPageTitle(), "Countries");

            TestLinkLogHandler.log(TestLinkMapping.COUNTRY_GRID_SHOULD_LOAD_SUCCESS);
        }
    }

    @And("I add new country details")
    public void i_add_new_country_details() {
        countryGridPage.clickAdd();
        countryPage.addNewValues();
    }

    @And("I save country")
    public void i_save_country() {
        countryPage.clickSaveButton();
    }

    @Then("New country should be created")
    public void new_country_should_be_created() {
        try {
            NotificationService.verifySuccessStatusOfAction(countryPage);
            countryPage.verifyDBData();

            countryPage.clickCloseButton();
        } catch (AssertionError e) {
            onActionFail(countryPage);
        }
    }

    @And("I approve country")
    public void i_approve_country() {
        countryGridPage.clickApprove();
    }

    @Then("Country should be approved")
    public void country_should_be_approved() {
        NotificationService.verifySuccessStatusOfAction(countryPage);
    }

    @When("I click edit country")
    public void i_click_edit_country() {
        countryGridPage.clickGridSearchBtn();
        countryGridPage.filter("filter_m05Name", countryPage.getCountryNameValue());

        TestLinkLogHandler.log(TestLinkMapping.COUNTRY_GRID_SHOULD_REFRESHED_SUCCESSFULLY);
        TestLinkLogHandler.log(TestLinkMapping.GRID_SHOULD_FILTERED_SUCCESSFULLY);

        countryGridPage.clickEdit();
    }

    @When("I update country details")
    public void i_update_country_details() {
        countryPage.updateValues();
    }

    @Then("Country should be updated")
    public void country_should_be_updated() {
        try {
            NotificationService.verifySuccessStatusOfAction(countryPage);
            countryPage.verifyDBData();

            countryPage.clickCloseButton();

            TestLinkLogHandler.log(TestLinkMapping.COUNTRIES_SHOULD_BE_EDITED_SUCCESSFULLY);
        } catch (AssertionError e) {
            onActionFail(countryPage);
        }
    }

    @When("I perform basic approval cycle in country list")
    public void i_perform_basic_approval_cycle_in_country_list() {
        ApprovalService.performBasicApproval(countryGridPage);
    }

    @And("approval cycle should be success")
    public void approval_cycle_should_be_success() {
        // TODO: [YD] Implement this
    }

    @And("I close country tab")
    public void i_close_country_tab() {
        countryPage.verifyStatusHistoryCount();
        countryGridPage.closeTab();
    }

    private void onActionFail(CountryPage countryPage) {
        countryPage.clickCloseButton();
        countryPage.confirmYes();
    }
}
