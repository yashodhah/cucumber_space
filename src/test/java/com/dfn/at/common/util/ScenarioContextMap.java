package com.dfn.at.common.util;

import com.dfn.at.common.beans.Context;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScenarioContextMap {
    private static Map<String, Object> scenarioContext = new ConcurrentHashMap<>();

    public static void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public static Object getContext(Context key) {
        return scenarioContext.get(key.toString());
    }

    public static Boolean isContains(Context key) {
        return scenarioContext.containsKey(key.toString());
    }
}
