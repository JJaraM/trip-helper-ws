package com.jjm.triphelper.service.impl;

import com.jjm.foursquare.entity.Response;
import com.jjm.foursquare.entity.Venue;
import com.jjm.foursquare.service.PlaceServiceFoursquare;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.parser.PlaceParser;
import com.jjm.triphelper.service.PlaceService;
import com.jjm.triphelper.service.VenueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class VenueServiceImpl implements VenueService {

    @Resource private PlaceParser placeParser;
    @Resource private PlaceServiceFoursquare serviceFoursquare;
    @Resource private PlaceService placeService;

    @Override
    public Set<Place> savePlaces(Response<Venue> venues) {
        Set<Place> places = placeParser.fetchNearPlacesByLocationNameFoursquare(venues.getItems());
        if (!places.isEmpty())
            places.forEach(placeService::save);
        return places;
    }

    @Override
    public Response<Venue> fetchNearVenuesByLocationName(String locationName) {
        return serviceFoursquare.fetchNearPlacesByLocationName(locationName);
    }
}
