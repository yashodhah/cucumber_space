package com.dfn.at.core.pages.master_data.country;

import com.dfn.at.common.beans.StatusHistoryRequest;
import com.dfn.at.common.beans.TestCategory;
import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.common.util.TestUtility;
import com.dfn.at.core.constants.TableEntityId;
import com.dfn.at.core.pages.common.BottomButtonI;
import com.dfn.at.core.pages.common.DialogPage;
import org.openqa.selenium.By;

public class CountryPage extends DialogPage {
    By title = By.className("mat-tab-label-content");

    By countryCode = By.id("m05Code");
    By countryName = By.id("m05Name");
    By accessLevel = By.id("m05AccessLevelIdV01");
    By accessLevel_val = By.id("m05AccessLevelIdV01_3");

    public CountryPage(BottomButtonI bottomButtonI) {
        super(bottomButtonI);
    }

    public String getTabHeader() {
        return ElementUtility.getText(title);
    }

    public void addNewValues() {
        this.setText(countryCode, TestUtility.getCreateValue(), "m05_code");
        this.setText(countryName, TestUtility.getCreateValue());
        this.selectDropdownValue(accessLevel, accessLevel_val);
    }

    public void updateValues() {
        ElementUtility.setText(countryName, TestUtility.getUpdateValue());
    }

    public String getCountryNameValue() {
        return TestUtility.getCreateValue();
    }

    public void updatePrimaryKey() {
        By idField = By.id("m05Id");
        this.updateDBFieldValueMap("m05_id", ElementUtility.getValue(idField));
    }

    public void verifyDBData() {
        updatePrimaryKey();
        super.verifyDBData(TestCategory.MASTER_DATA, "M05_COUNTRY", "Verify country DB data");
    }

    public void verifyStatusHistoryCount() {
        int entityId = (int) this.getValueFromDBFieldValueMap("m05_id");

        StatusHistoryRequest statusHistoryRequest = new StatusHistoryRequest(entityId, TableEntityId.M05_COUNTRY);

    }
}
