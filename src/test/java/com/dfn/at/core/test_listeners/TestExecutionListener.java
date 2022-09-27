package com.dfn.at.core.test_listeners;

import org.testng.IExecutionListener;

public class TestExecutionListener implements IExecutionListener {
    private String[] reportNames;

    public void onExecutionFinish() {
        setReportNames(reportNames);
    }

    public void setReportNames(String[] reportNames) {
        this.reportNames = reportNames;
    }
}
