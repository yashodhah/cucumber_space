package com.dfn.at.core.step_definitions;

import io.cucumber.java.en.And;

public class CommonStep {
    @And("I click save button")
    public void i_click_edit() {
        System.out.println("I click edit");
    }


    @And("I approve the record")
    public void i_approve_the_record() {
        System.out.println("I approve the record");
    }
}
