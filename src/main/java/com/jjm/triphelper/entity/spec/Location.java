/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.spec;

/**
 * The {@link Location} represents the all information related to place's location
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface Location extends SingularId {

    Double getLatitude();
    void setLatitude(Double latitude);

    Double getLongitude();
    void setLongitude(Double longitude);

}
