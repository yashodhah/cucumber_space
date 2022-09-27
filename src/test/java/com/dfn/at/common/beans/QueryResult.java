package com.dfn.at.common.beans;

import javax.sql.rowset.CachedRowSet;

public class QueryResult {
    private int noOfRows;
    private CachedRowSet cachedRowSet;

    public QueryResult(CachedRowSet crs, int size) {
        this.cachedRowSet = crs;
        this.noOfRows = size;
    }

    public int getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }

    public CachedRowSet getCachedRowSet() {
        return cachedRowSet;
    }

    public void setCachedRowSet(CachedRowSet cachedRowSet) {
        this.cachedRowSet = cachedRowSet;
    }
}
