package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Place;
import java.util.Set;

public interface PlaceService {
    Set<Place> fetchNearPlacesByLocationNameAndCategoryId(final String locationName, final String categoryId);
    Set<Place> fetchNearPlacesByLocationName(String placeLocation);
    Place save(Place place);
    Place findById(String placeId);
    Place findByReferenceId(String referenceId);
    Place findByLocationAndCategories(String placeLocation, String categoryId);
}
