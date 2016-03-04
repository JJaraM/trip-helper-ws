package com.jjm.triphelper.repository;

import com.jjm.triphelper.entity.spec.Place;
import java.util.Set;

public interface PlaceRepository {
    Set<Place> fetchNearPlacesByLocationName(String location);
    Place saveEntity(Place place);
}
