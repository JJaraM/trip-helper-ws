package com.jjm.triphelper.service.strategy;

import com.jjm.foursquare.entity.Response;
import com.jjm.foursquare.entity.Venue;
import com.jjm.foursquare.service.PlaceServiceFoursquare;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.parser.PlaceParser;
import com.jjm.triphelper.service.PlaceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Set;

@Service(value = "placeService")
public class PlaceServiceStrategy implements PlaceService {

    @Resource private PlaceServiceFoursquare serviceFoursquare;
    @Resource private PlaceService placeServiceJPA;
    @Resource private PlaceParser placeParser;

    @Override
    public Set<Place> fetchNearPlacesByLocationName(String locationName) {
        Set<Place> places = placeServiceJPA.fetchNearPlacesByLocationName(locationName);
        if (places.isEmpty()) {
            Response<Venue> response = serviceFoursquare.fetchNearPlacesByLocationName(locationName);
            places = placeParser.fetchNearPlacesByLocationNameFoursquare(response.getItems());
            if (!places.isEmpty()) {
                places.forEach(this::save);
            }
        }
        return places;
    }

    @Override
    public Place save(Place place) {
        return placeServiceJPA.save(place);
    }
}