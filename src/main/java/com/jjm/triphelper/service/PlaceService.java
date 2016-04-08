package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Place;
import java.util.Set;

public interface PlaceService {
    Set<Place> fetchNearPlacesByLocationName(final String locationName);
    Set<Place> fetchNearPlacesByLocationNameAndCategoryId(final String locationName, final String categoryId);
    Place save(Place place);
    Place findById(String placeId);
    Place findByReferenceId(String referenceId);
}
