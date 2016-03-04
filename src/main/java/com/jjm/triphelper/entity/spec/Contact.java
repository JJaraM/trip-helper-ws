/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.spec;

/**
 * The {@link Contact} represents the contact information to found the {@link Place}
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface Contact extends SingularId {

    String getPhone();
    void setPhone(String phone);

    String getFacebookUsername();
    void setFacebookUsername(String facebookUsername);

    String getFacebookName();
    void setFacebookName(String facebookName);

    Place getPlace();
    void setPlace(Place place);

}
