package com.jjm.triphelper.entity.spec;

import java.util.Set;

public interface State extends SingularId {

    String getName();
    void setName(String name);

    City getCity();
    void setCity(City city);

    Set<Place> getPlaces();
    void setPlaces(Set<Place> places);

    Place addPlace(Place place);
    Place removePlace(Place place);
}
