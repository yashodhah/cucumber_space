package com.dfn.at.core.step_definitions.master_data;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.common.services.TestLinkLogHandler;
import com.dfn.at.core.constants.TestLinkMapping;
import com.dfn.at.core.pages.common.GridPage;
import com.dfn.at.core.pages.master_data.country.CityPage;
import com.dfn.at.core.services.NotificationService;
import com.dfn.at.core.step_definitions.BaseStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CityStep extends BaseStep {
    GridPage countryGridPage = (GridPage) BeanRegistryCore.getInstance().getBean(GridPage.class, "COUNTRY_LIST", "country");
    CityPage cityPage = (CityPage) BeanRegistryCore.getInstance().getBean(CityPage.class);

    @And("I navigate to the city page")
    public void i_navigate_to_the_city_page() {
        countryGridPage.clickEdit();
        cityPage.navigateToCity();
    }

    @And("I add new city details")
    public void i_enter_new_values_for_city() {
        cityPage.getDataViewGridPage().clickGridAddButton();
        cityPage.addNewValues();
    }

    @And("I save city")
    public void i_save_new_city() {
        cityPage.clickSaveButton();
    }

    @And("New city should be created")
    public void new_city_should_be_created() {
        NotificationService.verifySuccessStatusOfAction(cityPage);
        TestLinkLogHandler.log(TestLinkMapping.CITY_SHOULD_BE_ADDED_SUCCESSFULLY);
    }

    @And("I approve city")
    public void i_approve_the_newly_created_city() {
        cityPage.getDataViewGridPage().clickGridApproveButton();
    }

    @Then("City should be approved")
    public void new_city_should_be_approved() {
        NotificationService.verifySuccessStatusOfAction(cityPage);
    }

    @When("I click edit city")
    public void i_click_edit_city() {
        cityPage.getDataViewGridPage().clickGridEditButton();
    }

    @When("I update city details")
    public void i_edit_the_created_city() {
        cityPage.updateNewValues();
    }

    @Then("City should be updated")
    public void created_city_should_be_updated() {
        NotificationService.verifySuccessStatusOfAction(cityPage);
        TestLinkLogHandler.log(TestLinkMapping.CITIES_SHOULD_BE_EDITED_SUCCESSFULLY);
    }

    @And("I press close city")
    public void i_press_close() {
        cityPage.clickCloseButton();
    }
}
