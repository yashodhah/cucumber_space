package com.dfn.at.core.step_definitions.master_data;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.core.constants.MenuId;
import com.dfn.at.core.pages.common.GridPage;
import com.dfn.at.core.pages.master_data.symbol_management.PriceQtyFactorPage;
import com.dfn.at.core.services.ApprovalService;
import com.dfn.at.core.services.NotificationService;
import com.dfn.at.core.step_definitions.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class PriceFactorStep extends BaseStep {
    GridPage priceQtyFactorGridPage;
    PriceQtyFactorPage priceQtyFactorPage;

    public PriceFactorStep() {
        priceQtyFactorGridPage = (GridPage) BeanRegistryCore.getInstance().getBean(GridPage.class, "PRICE_QTY_FACTORS", "price_quantity_factors");
        priceQtyFactorPage = (PriceQtyFactorPage) BeanRegistryCore.getInstance().getBean(PriceQtyFactorPage.class);
    }

    @Given("I navigate to the price and quantity factor page")
    public void i_navigate_to_the_price_and_quantity_factor_page() {
        this.navigateToPage(MenuId.MASTER_DATA, MenuId.MASTER_DATA_SYMBOL_MGMT, MenuId.MASTER_DATA_SYMBOL_MGMT_PRICE_QTY_FACTOR);
        Assert.assertEquals(priceQtyFactorGridPage.getPageTitle(), "Price & Quantity factors");
    }

    @And("I add new price factor details")
    public void i_add_new_price_factor_details() {
        priceQtyFactorGridPage.clickAdd();
        priceQtyFactorPage.addNewValues();
    }

    @Given("I save price factor")
    public void i_save_price_factor() {
        priceQtyFactorPage.clickSaveButton();
    }

    @And("New price factor should be created")
    public void new_price_factor_should_be_created() {
        try {
            NotificationService.verifySuccessStatusOfAction(priceQtyFactorPage);
            priceQtyFactorPage.clickCloseButton();
        } catch (AssertionError e) {
            onActionFail(priceQtyFactorPage);
        }
    }

    @Then("I approve the price factor")
    public void i_approve_the_price_factor() {
        priceQtyFactorGridPage.clickApprove();
    }

    @Then("Price factor should be approved")
    public void price_factor_should_be_approved() {
        NotificationService.verifySuccessStatusOfAction(priceQtyFactorPage);
    }

    @When("I click edit price factor")
    public void i_click_edit_price_factor() {
        priceQtyFactorPage.setPrimaryKey(priceQtyFactorGridPage.getFirstGridValue("m39Id"));

        priceQtyFactorGridPage.clickGridSearchBtn();
        priceQtyFactorGridPage.filter("filter_m39Name", priceQtyFactorPage.getNameValue());

        priceQtyFactorPage.setPrimaryKey(priceQtyFactorGridPage.getFirstGridValue("m39Id"));
        priceQtyFactorGridPage.clickEdit();
    }

    @When("I update price factor details")
    public void i_update_price_factor_details() {
        priceQtyFactorPage.updateValues();
    }

    @Then("Price factor should be updated")
    public void price_factor_should_be_updated() {
        try {
            NotificationService.verifySuccessStatusOfAction(priceQtyFactorPage);
            priceQtyFactorPage.clickCloseButton();
        } catch (AssertionError e) {
            onActionFail(priceQtyFactorPage);
        }
    }

    @When("I perform basic approval cycle in price factor list")
    public void i_perform_basic_approval_cycle_in_price_factor_list() {
        ApprovalService.performBasicApproval(priceQtyFactorGridPage);
    }

    @And("I close price factor tab")
    public void i_close_price_factor_tab() {
        this.priceQtyFactorGridPage.closeTab();
    }

    private void onActionFail(PriceQtyFactorPage priceQtyFactorPage) {
        priceQtyFactorPage.clickCloseButton();
        priceQtyFactorPage.confirmYes();
    }
}
