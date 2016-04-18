package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.CityJPA;
import org.springframework.stereotype.Component;
import java.util.HashSet;

@Component
public class CityFactory {

    public CityJPA create(Venue venue) {
        CityJPA city = new CityJPA();
        city.setName(venue.getLocation().getCity());
        city.setStates(new HashSet<>());
        return city;
    }
}
