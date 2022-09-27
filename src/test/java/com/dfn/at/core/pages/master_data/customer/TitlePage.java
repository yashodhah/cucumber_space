package com.dfn.at.core.pages.master_data.customer;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.pages.common.BottomButtonI;
import com.dfn.at.core.pages.common.DialogPage;
import org.openqa.selenium.By;

public class TitlePage extends DialogPage  {
    By titleDescription = By.id("m130Description");
    By titleDescriptionAr = By.id("m130DescriptionLang");

    private String titleDescriptionValue = "AUTO_TD";
    private String titleDescriptionValueAr = "AUTO_TD_AR";

    public TitlePage(BottomButtonI bottomButtonI) {
        super(bottomButtonI);
    }

    public void addNewValues() {
        ElementUtility.setText(titleDescription, titleDescriptionValue);
        ElementUtility.setText(titleDescriptionAr, titleDescriptionValueAr);
    }

    public String getNameValue() {
        return titleDescriptionValue;
    }
    public void updateValues() {
        ElementUtility.setText(titleDescriptionAr, "AUTO_TD_AR_UPDATED");

    }
}
