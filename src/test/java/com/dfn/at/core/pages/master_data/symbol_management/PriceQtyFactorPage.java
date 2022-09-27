package com.dfn.at.core.pages.master_data.symbol_management;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.common.util.TestUtility;
import com.dfn.at.core.pages.common.BottomButtonI;
import com.dfn.at.core.pages.common.DialogPage;
import org.openqa.selenium.By;

public class PriceQtyFactorPage extends DialogPage {
    By name = By.id("m39Name");
    By nameAr = By.id("m39NameLang");

    public PriceQtyFactorPage(BottomButtonI bottomButtonI) {
        super(bottomButtonI);
    }

    public void addNewValues() {
        ElementUtility.setText(name, TestUtility.getCreateValue());
        ElementUtility.setText(nameAr, TestUtility.getCreateValue());
    }

    public void updateValues() {
        ElementUtility.setText(name, TestUtility.getUpdateValue());
    }

    public String getNameValue() {
        return TestUtility.getCreateValue();
    }

    public void setPrimaryKey(String pValue) {
        String pKey = pValue;
    }
}
