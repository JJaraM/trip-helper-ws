package com.jjm.triphelper.service;

import org.json.JSONObject;

public class JSONObjectUtils {

    public static String getString(JSONObject jsonObject, String key) {
        String value = null;
        if (jsonObject.has(key)) {
            value = jsonObject.getString(key);
        }
        return value;
    }

    public static Double getDouble(JSONObject jsonObject, String key) {
        Double value = null;
        if (jsonObject.has(key)) {
            value = jsonObject.getDouble(key);
        }
        return value;
    }

}
