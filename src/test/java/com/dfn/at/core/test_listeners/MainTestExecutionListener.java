package com.dfn.at.core.test_listeners;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.common.delivery.EmailNotificationService;
import com.dfn.at.common.delivery.ReportService;
import com.dfn.at.common.util.FileHandler;
import com.dfn.at.core.constants.EnvironmentConstants;

import java.util.List;

public class MainTestExecutionListener extends TestExecutionListener {
    public void onExecutionFinish() {
        sendReportAsEmail();
//        TestUtility.clearResources();
    }

    private void sendReportAsEmail() {
        EmailNotificationService emailNotificationService = (EmailNotificationService) BeanRegistryCore.getInstance().getBean(EmailNotificationService.class);
        ReportService reportService = (ReportService) BeanRegistryCore.getInstance().getBean(ReportService.class);

        try {
            reportService.mergeAllReports("master-data.cucumber.json", "users.cucumber.json");
            String emailReport = reportService.getEmailReport();
            FileHandler.zipDirectory(EnvironmentConstants.REPORT_MERGE_PATH, EnvironmentConstants.REPORT_ZIP_PATH);
            FileHandler.zipDirectory(EnvironmentConstants.REPORT_ZIP_PATH, EnvironmentConstants.REPORT_ZIP_DELIVERY_PATH + "/at-automation-report.zip"); // Temp hack to bypass mail server reading zip files
            List<String> attachmentFiles = reportService.getAttachmentFilePathList();

            emailNotificationService.sendEmail(emailReport, attachmentFiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
