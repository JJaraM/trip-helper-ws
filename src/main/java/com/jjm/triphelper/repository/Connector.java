package com.jjm.triphelper.repository;

import com.jjm.triphelper.domain.Parameter;
import org.json.JSONObject;

public interface Connector {
    JSONObject get(String path, Parameter... parameters);
    String getUrl(String path, Parameter... parameters);
}
