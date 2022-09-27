package com.dfn.at.common.delivery;

import com.dfn.at.common.util.TestUtility;
import com.dfn.at.common.util.WebDriverManager;
import com.dfn.at.core.constants.EnvironmentConstants;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportService {
    public void recordFailedTest(ITestResult iTestResult) {
        try {
            TestUtility.getScreenshot(WebDriverManager.getDriver(), iTestResult.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mergeAllReports(String... reportNames) {
        File reportOutputDirectory = new File(EnvironmentConstants.REPORT_BASE_PATH);

        List<String> jsonFiles = getReportList(reportNames);
        Configuration configuration = getReportConfigurations(reportOutputDirectory);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);

        reportBuilder.generateReports();
    }

    // TODO: [YD] Remove unnecessary links and enhance email template
    public String getEmailReport() {
        HtmlParser htmlParser = new HtmlParser();

        try {
            Document generateReportDoc = htmlParser.loadHtml(EnvironmentConstants.REPORT_MERGE_PATH + "/overview-features.html");
            Document emailTemplate = htmlParser.loadHtml(getClass().getResource("/core/email_template.html").getPath());

            Element generatedSummaryTable = htmlParser.getFirstElement(generateReportDoc, "div#report");
            Element templateSummary = htmlParser.getFirstElement(emailTemplate, "div#test_summary");

            templateSummary.html(generatedSummaryTable.html());

            return emailTemplate.html();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public List<String> getAttachmentFilePathList() {
        List<String> results = new ArrayList<>();

        addScreenshotPaths(results);
        addGeneratedReportZipPath(results);

        return results;
    }

    private void addScreenshotPaths(List<String> results) {
        File[] files = new File(EnvironmentConstants.SCREENSHOTS_PATH).listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    results.add(file.getPath());
                }
            }
        }
    }

    private void addGeneratedReportZipPath(List<String> results) {
        File file = new File(EnvironmentConstants.REPORT_ZIP_DELIVERY_PATH + "/at-automation-report.zip");

        if (file.isFile()) {
            results.add(file.getPath());
        }
    }

    private static Configuration getReportConfigurations(File reportOutputDirectory) {
        Configuration configuration = new Configuration(reportOutputDirectory, EnvironmentConstants.TITLE);

        configuration.addClassifications("Platform", System.getProperty("os.name"));
        configuration.addClassifications("Browser", EnvironmentConstants.BROWSER);

        return configuration;
    }

    private static List<String> getReportList(String[] reportNames) {
        List<String> jsonFiles = new ArrayList<>();

        for (String reportName : reportNames) {
            jsonFiles.add("target/cucumber-reports/" + reportName);
        }

        return jsonFiles;
    }
}