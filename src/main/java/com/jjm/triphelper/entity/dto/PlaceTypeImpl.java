package com.jjm.triphelper.entity.dto;

import com.jjm.triphelper.entity.spec.PlaceType;

public class PlaceTypeImpl implements PlaceType {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
