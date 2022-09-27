package com.dfn.at.core.pages.common;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.pages.BasePage;
import org.openqa.selenium.By;

public class ApprovalConfirmationPage extends BasePage {
    By btnYes = By.id("dependant_col_list_btn_ok");
    By btnNo = By.id("dependant_col_list_btn_cancel");

    public void clickConfirm() {
        ElementUtility.click(btnYes);
    }

    public void clickCancel() {
        ElementUtility.click(btnNo);
    }
}
