package com.dfn.at.core.pages.common;

import com.dfn.at.common.util.ElementUtility;
import com.dfn.at.core.pages.BasePage;
import com.dfn.at.core.services.NotificationService;
import org.openqa.selenium.By;

public class ColumnWiseApprovalPage extends BasePage {
    BasePage basePage;
    
    By lblRowCount = By.id("total_row_count");
    By chkAll = By.id("chk_select_all");
    By btnApprove = By.id("col_wise_btn_approve");
    By btnClose = By.id("col_wise_btn_close");

    public ColumnWiseApprovalPage(BasePage basePage) {
        this.basePage = basePage;
    }

    public void performColumnWiseApproval() {
        String rowCount = ElementUtility.getText(lblRowCount);
        ApprovalConfirmationPage approvalConfirmationPage = new ApprovalConfirmationPage();

        while (Integer.parseInt(rowCount) > 0) {
            ElementUtility.click(chkAll);
            ElementUtility.click(btnApprove);

            approvalConfirmationPage.clickConfirm();
            NotificationService.verifySuccessStatusOfAction(this.basePage);

            rowCount = ElementUtility.getText(lblRowCount);
        }

        ElementUtility.click(btnClose);
    }
}
