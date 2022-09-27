package com.dfn.at.core.services;

import com.dfn.at.common.services.LogHandler;
import com.dfn.at.common.services.LogHandlerI;
import com.dfn.at.core.pages.BasePage;
import com.dfn.at.core.pages.common.GridPage;

public class ApprovalService {
    final static LogHandlerI logger = new LogHandler(NotificationService.class);

    public static void performBasicApproval(GridPage gridPage) {
        gridPage.clickMarkForDelete();
        verifyAction(gridPage);

        gridPage.clickRestore();
        verifyAction(gridPage);

        gridPage.clickMarkForDelete();
        verifyAction(gridPage);

        gridPage.clickDelete();
        verifyAction(gridPage);

        gridPage.clickGrant();
        verifyAction(gridPage);

        gridPage.clickReject();
        verifyAction(gridPage);

        gridPage.clickGrant();
        verifyAction(gridPage);

        gridPage.clickApprove();
        verifyAction(gridPage);
    }

    private static void verifyAction(BasePage pageObject) {
        try {
            NotificationService.verifySuccessStatusOfAction(pageObject);
        } catch (Exception e) {
            logger.logError("Error in verifying actions in basic approvals");
        }
    }
}
