package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.LocationJPA;
import org.springframework.stereotype.Component;

@Component(value = "locationFactory")
public class LocationFactory {

    public LocationJPA create(Venue venue) {
        LocationJPA location = new LocationJPA();
        location.setLatitude(venue.getLocation().getLatitude());
        location.setLongitude(venue.getLocation().getLongitude());
        return location;
    }
}
