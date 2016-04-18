package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.PlaceJPA;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashSet;

@Component
public class PlaceFactory {

    public PlaceJPA create(Venue venue) {
        PlaceJPA place = new PlaceJPA();
        place.setReferenceId(venue.getReferenceId());
        place.setName(venue.getName());
        place.setCreatedDate(new Date());
        place.setUrl(venue.getUrl());
        place.setRating(venue.getRating());
        place.setPhotos(new HashSet<>());
        return place;
    }
}
