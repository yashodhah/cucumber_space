package com.dfn.at.common.services;

import com.dfn.at.common.beans.QueryResult;
import com.dfn.at.common.beans.StatusHistoryRequest;
import com.dfn.at.common.beans.TestCategory;
import com.dfn.at.core.constants.ApplicationConstants;
import org.testng.Assert;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBVerificationService implements DBVerificationServiceI {
    final static LogHandlerI logger = new LogHandler(DBVerificationService.class);

    private final String SPACE = " ";
    private final String SINGLE_QUOTE = "'";

    public void verifyDBData(TestCategory testCategory, Map<String, Object> filterParameters, String table, String message) {
        if (skipDbValidation(testCategory)) {
            return;
        }

        Assert.assertTrue(isDBDataUpdated(filterParameters, table), message);
    }

    public void verifyDBData(TestCategory testCategory, String query, String message) {
        if (skipDbValidation(testCategory)) {
            return;
        }

        Assert.assertTrue(isDBDataUpdated(query), message);
    }

    public boolean isDBDataUpdated(Map<String, Object> filterParameters, String table) {
        String query = getDataCountSqlQuery(filterParameters, table);
        return isDBDataUpdated(query);
    }

    public boolean isDBDataUpdated(String query) {
        QueryResult queryResult = getDBData(query);

        if (queryResult == null || queryResult.getNoOfRows() != 1) {
            return false;
        }

        return true;
    }

    public boolean verifyStatusHistoryCount(StatusHistoryRequest statusHistoryRequest) {
        Map<String, Object> filterParameters = new HashMap<>();

        filterParameters.put("a10_approval_entity_id", statusHistoryRequest.getApprovalEntityId());
        filterParameters.put("a10_entity_pk", statusHistoryRequest.getEntityPrimaryKey());

        if (statusHistoryRequest.getStatusChangedDate() != null) {
            filterParameters.put("a10_status_changed_date", statusHistoryRequest.getStatusChangedDate());
        }

        String query = getDataCountSqlQuery(filterParameters, "vw_a10_entity_status_history");
        QueryResult queryResult = getDBData(query);

        if (queryResult == null || queryResult.getNoOfRows() != 1) {
            return false;
        }

        return false;
    }

    public QueryResult getDBData(String query) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        QueryResult queryResult = null;

        try {
            connection = ConnectionManager.getConnectionManager().getHikariDataSourceConnection();
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);

            queryResult = new QueryResult(crs, crs.size());
        } catch (Exception e) {
            logger.logError("Error in validate db data", e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                logger.logError("Error in clearing connection", e);
            }
        }

        return queryResult;
    }

    private String getDataCountSqlQuery(Map<String, Object> filterParameters, String table) {
        String[] filterFields = filterParameters.keySet().toArray(new String[0]);
        Object[] filterValues = filterParameters.values().toArray();

        StringBuilder query = new StringBuilder();

        query.append("SELECT count(*) FROM");
        query.append(SPACE);
        query.append(table);
        query.append(SPACE);

        query.append("WHERE");

        for (int i = 0; i < filterFields.length - 1; i++) {
            query.append(SPACE);
            query.append(filterFields[i]);
            query.append(" = ");
            query.append(SINGLE_QUOTE + filterValues[i] + SINGLE_QUOTE);
            query.append(SPACE);
            query.append("AND");
        }

        query.append(SPACE);
        query.append(filterFields[filterFields.length - 1]);
        query.append(" = ");
        query.append(SINGLE_QUOTE + filterValues[filterFields.length - 1] + SINGLE_QUOTE);

        return query.toString();
    }

    private boolean skipDbValidation(TestCategory testCategory) {
        return ApplicationConstants.SKIP_VALIDATE_DB_DATA || !isTestCategoryEnabled(testCategory);
    }

    private boolean isTestCategoryEnabled(TestCategory testCategory) {
        if (ApplicationConstants.DB_VERIFY_CATEGORIES.length == 0) {
            return false;
        }

        for (TestCategory cat : ApplicationConstants.DB_VERIFY_CATEGORIES) {
            if (cat == testCategory) {
                return true;
            }
        }

        return false;
    }
}
