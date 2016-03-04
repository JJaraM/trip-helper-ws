/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.controller.exceptions;

/**
 * The {@code PlaceNotFoundException} represents the exception when there is not place
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public class PlaceNotFoundException extends RuntimeException {

    private String place;

    public PlaceNotFoundException(String place) {
        super("We did not found any place(s) for " + place);
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
}
