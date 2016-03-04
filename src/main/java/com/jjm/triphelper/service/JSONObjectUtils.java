/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.service;

import org.json.JSONObject;

/**
 * The {@code JSONObjectUtils} represents the utility class to manage the all basic operation of {@link JSONObject}
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
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
