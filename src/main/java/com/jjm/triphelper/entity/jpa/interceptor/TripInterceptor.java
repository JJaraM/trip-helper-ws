package com.jjm.triphelper.entity.jpa.interceptor;


import com.jjm.chameleon.utils.InterceptorSerializer;
import com.jjm.triphelper.service.impl.CryptoServiceIml;

public class TripInterceptor implements InterceptorSerializer {
    @Override
    public Object getValue(Object value) {
        return new CryptoServiceIml().encrypt(String.valueOf(value) , "TRIP_ID");
    }
}