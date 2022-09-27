package com.dfn.at.core.pages.master_data.customer;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.pages.common.BottomButtonI;
import com.dfn.at.core.pages.common.DialogPage;
import org.openqa.selenium.By;

public class MaritalStatusPage extends DialogPage {
    By maritalStatusDescription = By.id("m128Description");
    private String maritalStatusDescriptionValue = "AUTO_MS";

    public MaritalStatusPage(BottomButtonI bottomButtonI) {
        super(bottomButtonI);
    }

    public void addNewValues() {
        ElementUtility.setText(maritalStatusDescription, maritalStatusDescriptionValue);

    }

    public String getNameValue() {
        return maritalStatusDescriptionValue;
    }

    public void updateValues() {
        ElementUtility.setText(maritalStatusDescription, "AUTO_MS_UPDATED");

    }
}
