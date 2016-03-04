/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.spec;

/**
 * The {@link Photo} represents the specification of photo
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface Photo extends SingularId, ReferenceId {

    String getPrefix();
    void setPrefix(String prefix);

    String getSuffix();
    void setSuffix(String suffix);

    Double getWidth();
    void setWidth(Double width);

    Double getHeight();
    void setHeight(Double height);

    Place getPlace();
    void setPlace(Place place);
}
