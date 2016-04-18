package com.jjm.triphelper.entity.spec;

import java.util.Set;

public interface TripInfo extends SingularId {
    String getPlaceLocation();
    void setPlaceLocation(String placeLocation);

    String getPicture();
    void setPicture(String picture);

    Set<Trip> getTrips();
    void setTrips(Set<Trip> trips);
}
