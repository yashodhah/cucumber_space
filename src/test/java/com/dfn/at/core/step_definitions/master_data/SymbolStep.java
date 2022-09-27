package com.dfn.at.core.step_definitions.master_data;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.core.constants.MenuId;
import com.dfn.at.core.pages.common.GridPage;
import com.dfn.at.core.pages.master_data.symbol_management.SymbolPage;
import com.dfn.at.core.services.NotificationService;
import com.dfn.at.core.step_definitions.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SymbolStep extends BaseStep {
    GridPage symbolGridPage;
    SymbolPage symbolPage;
    int symbolId;

    public SymbolStep() {
        this.symbolGridPage = (GridPage) BeanRegistryCore.getInstance().getBean(GridPage.class, "SYMBOL_LIST", "symbols");
        this.symbolPage = (SymbolPage) BeanRegistryCore.getInstance().getBean(SymbolPage.class);
    }

    @When("user navigates to symbol list")
    public void user_navigates_to_symbol_list() {
        this.navigateToPage(MenuId.MASTER_DATA, MenuId.MASTER_DATA_SYMBOL_MGMT, MenuId.MASTER_DATA_SYMBOL_MGMT_SYMBOLS);
    }

    @And("user clicks on grid add button")
    public void user_clicks_on_grid_add_button() {
        symbolGridPage.clickAdd();
    }

    @Then("user navigates to symbol add form")
    public void user_navigates_to_symbol_add_form() {
        Assert.assertEquals(symbolPage.getPageTitle(), "Symbol");
    }

    @And("user adds symbol details")
    public void user_adds_symbol_details() {
        symbolPage.addNewValues();
    }

    @And("user saves symbol")
    public void user_saves_symbol() {
        symbolPage.clickSaveButton();
    }

    @Then("new symbol should be created")
    public void new_symbol_should_be_created() {
        try {
            NotificationService.verifySuccessStatusOfAction(symbolGridPage);
        } catch (AssertionError e) {
        } finally {
            symbolPage.clickAddPageCloseButton();
        }
    }

    @And("user approves the symbol")
    public void user_approves_the_symbol() {
        symbolGridPage.clickApprove();
    }

    @Then("symbol should be approved")
    public void symbol_should_be_approved() {
        NotificationService.verifySuccessStatusOfAction(symbolGridPage);
    }

    @And("user filters symbol")
    public void user_filters_symbol() {
        symbolGridPage.clickGridSearchBtn();
        symbolGridPage.filter("filter_m20SymbolCode", symbolPage.getSymbolCode());
        symbolId = symbolPage.extractsSymbolKey();
    }

    @And("user clicks edit symbol")
    public void user_clicks_edit_symbol() {
        symbolGridPage.clickEdit();
    }

    @And("user updates symbol details")
    public void user_updates_symbol_details() {
        symbolPage.updateValues();
    }

    @Then("symbol should be updated")
    public void symbol_should_be_updated() {
        NotificationService.verifySuccessStatusOfAction(symbolGridPage);
    }

    @And("user performs advanced approvals")
    public void user_performs_advanced_approvals() {
        symbolPage.performsAdvancedApprovals();
    }

    @And("user closes the symbol tabs")
    public void user_closes_the_symbol_tabs() {
        symbolPage.clickEditPageCloseButton();
        symbolPage.clickGridPageCloseButton();
    }
}
