package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Place;
import java.util.Set;

public interface PlaceService {
    Set<Place> fetchNearPlacesByLocationName(final String locationName);
    Place save(Place place);
}
