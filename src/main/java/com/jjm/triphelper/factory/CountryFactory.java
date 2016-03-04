package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.CountryJPA;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CountryFactory {

    public CountryJPA create(Venue venue) {
        CountryJPA country = new CountryJPA();
        country.setName(venue.getLocation().getCountry());
        country.setCode(venue.getLocation().getCc());
        country.setCities(new HashSet<>());
        return country;
    }
}
