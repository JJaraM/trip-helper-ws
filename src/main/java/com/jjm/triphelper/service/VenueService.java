package com.jjm.triphelper.service;

import com.jjm.foursquare.entity.Response;
import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.spec.Place;
import java.util.Set;

public interface VenueService {
    Set<Place> savePlaces(Response<Venue> venues);
    Response<Venue> fetchNearVenuesByLocationName(final String locationName);
}
