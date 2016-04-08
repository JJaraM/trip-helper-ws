package com.jjm.triphelper.entity.spec;

import java.util.Set;

public interface Category extends SingularId, ReferenceId {
    String getName();
    void setName(String name);

    Set<Place> getPlaces();
    void setPlaces(Set<Place> places);
}
