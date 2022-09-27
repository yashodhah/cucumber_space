package com.dfn.at.common.util;

import com.dfn.at.common.beans.BeanRegistryCore;
import com.dfn.at.common.delivery.ReportService;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestEventListener implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSuccess(ITestResult iTestResult) {
    }

    public void onTestFailure(ITestResult iTestResult) {
        getInstance().recordFailedTest(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    public void onStart(ITestContext iTestContext) {
    }

    public void onFinish(ITestContext iTestContext) {
    }

    private static ReportService getInstance() {
        return (ReportService) BeanRegistryCore.getInstance().getBean(ReportService.class);
    }
}