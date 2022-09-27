package com.dfn.at.core.pages.master_data.country;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.common.util.TestUtility;
import com.dfn.at.core.pages.common.BottomButtonI;
import com.dfn.at.core.pages.common.DataViewGridPage;
import com.dfn.at.core.pages.common.DialogPage;
import org.openqa.selenium.By;

public class CityPage extends DialogPage {
    By tab = By.xpath("//dfnat-country//mat-tab-header//div[@class='mat-tab-labels']//div[2]");

    private DataViewGridPage dataViewGridPage;

    By cityName = By.id("m06Name");
    By cityNameArabic = By.id("m06NameLang");

    public CityPage(BottomButtonI bottomButtonI) {
        super(bottomButtonI);
    }

    public void addNewValues() {
        ElementUtility.setText(cityName, TestUtility.getCreateValue());
        ElementUtility.setText(cityNameArabic, TestUtility.getCreateValue());
    }

    public void updateNewValues() {
        ElementUtility.setText(cityName, TestUtility.getUpdateValue());
    }

    public void navigateToCity() {
        ElementUtility.click(tab);
    }

    public void setDataViewGridPage(DataViewGridPage dataViewGridPage) {
        this.dataViewGridPage = dataViewGridPage;
    }

    public DataViewGridPage getDataViewGridPage() {
        return dataViewGridPage;
    }
}
