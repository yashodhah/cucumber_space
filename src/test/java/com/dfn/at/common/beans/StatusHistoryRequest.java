package com.dfn.at.common.beans;

import java.util.Date;

public class StatusHistoryRequest {
    private int approvalEntityId;
    private int entityPrimaryKey;
    private Date statusChangedDate;

    public StatusHistoryRequest() {
    }

    public StatusHistoryRequest(int approvalEntityId, int entityPrimaryKey) {
        this.approvalEntityId = approvalEntityId;
        this.entityPrimaryKey = entityPrimaryKey;
    }

    public int getApprovalEntityId() {
        return approvalEntityId;
    }

    public void setApprovalEntityId(int approvalEntityId) {
        this.approvalEntityId = approvalEntityId;
    }

    public int getEntityPrimaryKey() {
        return entityPrimaryKey;
    }

    public void setEntityPrimaryKey(int entityPrimaryKey) {
        this.entityPrimaryKey = entityPrimaryKey;
    }

    public Date getStatusChangedDate() {
        return statusChangedDate;
    }

    public void setStatusChangedDate(Date statusChangedDate) {
        this.statusChangedDate = statusChangedDate;
    }
}
