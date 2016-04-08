/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.spec;

import java.util.Set;

/**
 * The {@link User} represents the specification of user
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface User extends SingularId {

    String getEmail();
    void setEmail(String email);

    String getPassword();
    void setPassword(String password);

    String getName();
    void setName(String name);

    Set<Trip> getTrips();
    void setTrips(Set<Trip> trips);

    Trip addTrip(Trip trip);
    Trip removeTrip(Trip trip);

    Set<Trip> getShareTrips();
    void setShareTrips(Set<Trip> shareTrips);
}
