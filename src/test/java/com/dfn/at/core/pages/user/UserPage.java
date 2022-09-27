package com.dfn.at.core.pages.user;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.common.util.TestUtility;
import com.dfn.at.core.pages.common.DialogPage;
import org.openqa.selenium.By;

public class UserPage extends DialogPage {
    By userFullName = By.id("u17FullName");
    By ddDepartment = By.id("dropdown_u17DepartmentIdM12_");
    By departmentValue = By.id("u17DepartmentIdM12_89");

    By saveButton = By.name("emp-gen-save-btn");

    public void addNewValues() {
        ElementUtility.setText(userFullName, TestUtility.getCreateValue());
        this.selectDropdownValue(ddDepartment, departmentValue);
    }

    public void clickSaveButton() {
        ElementUtility.click(saveButton);
    }
}
