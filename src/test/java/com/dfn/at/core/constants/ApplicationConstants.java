// These constants are system master data loaded from dump data

package com.dfn.at.core.constants;

import com.dfn.at.common.beans.TestCategory;

public class ApplicationConstants {
    // Default Master Data Constants
    public static String INSTRUMENT_TYPE_ID = "4"; // CS
    public static String PRICE_INSTRUMENT_TYPE_ID = "1"; // CS
    public static String CURRENCY_ID = "17"; //SAR | This should be 19 in actual master data. In 192.168.14.214 Dev server it is 17
    public static String NATIONALITY_ID = "2"; // Saudi Arabia
    public static String PRICE_TYPE_ID = "1"; // Exchange
    public static String SETTLE_CATEGORY_ID = "0"; // Default
    public static String TRADE_TYPE_ID = "1";

    //
    public static int DEFAULT_CUSTOMER_ID = 1;
    public static boolean USE_CREATED_ENTITY = false;

    // SQL Script Based Constants
    public static String EXCHANGE_ID = "1";
    public static String EXCHANGE_CODE = "TDWL";
    public static String MARKET_ID = "1"; // In 192.168.14.214 Dev server it is 17
    public static String MARKET_CODE = "ALL";

    public static String CREATE_VALUE = "AUTOMATION";
    public static String CREATE_VALUE_S = "AUTO";
    public static String UPDATE_VALUE = "AUTOMATION_U";
    public static String UPDATE_VALUE_S = "AUTO_U";

    // Kill switches for db verification
    public static boolean SKIP_VALIDATE_DB_DATA = false;
    public static TestCategory[] DB_VERIFY_CATEGORIES = {TestCategory.MASTER_DATA};
}
