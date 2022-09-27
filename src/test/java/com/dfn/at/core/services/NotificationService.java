package com.dfn.at.core.services;

import com.dfn.at.common.services.LogHandler;
import com.dfn.at.common.services.LogHandlerI;
import com.dfn.at.core.pages.BasePage;
import org.testng.Assert;

public class NotificationService {
    final static LogHandlerI logger = new LogHandler(NotificationService.class);

    public final static String SAVE_SUCCESS_NOTIFICATION = "success";

    public static void verifySuccessStatusOfAction(BasePage pageObject) throws AssertionError {
        String notificationSummary = pageObject.getNotificationSummary();

        try {
            Assert.assertTrue(notificationSummary.contains(SAVE_SUCCESS_NOTIFICATION));
        } catch (AssertionError e) {
            logger.logError("Notification Summary : expected [" + SAVE_SUCCESS_NOTIFICATION + "] but found [" + notificationSummary + "]", e);
            throw e;
        } catch (Exception e) {
            logger.logError("Error in verifying success status of action performed");
            throw e;
        } finally {
            pageObject.closeNotification();
        }
    }
}
