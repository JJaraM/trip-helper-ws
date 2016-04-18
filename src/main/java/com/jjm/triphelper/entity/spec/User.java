package com.jjm.triphelper.entity.spec;

import java.util.Set;

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

    Set<Travel> getAllTravels();
}
