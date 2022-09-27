package com.dfn.at.core.services;

import com.dfn.at.core.pages.common.GridPage;

public interface ApprovalServiceI {
    void performBasicApproval(GridPage gridPage, String gridName, String statusColumn);
}
