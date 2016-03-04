package com.jjm.triphelper.parser;

import com.jjm.foursquare.entity.*;
import com.jjm.chameleon.query.ChameleonQueryManager;
import com.jjm.foursquare.entity.Photo;
import com.jjm.triphelper.entity.spec.*;
import com.jjm.triphelper.factory.*;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.*;

@Component
public class PlaceParser {

    @Resource private PlaceFactory placeFactory;
    @Resource private ContactFactory contactFactory;
    @Resource private LocationFactory locationFactory;
    @Resource private CountryFactory countryFactory;
    @Resource private CityFactory cityFactory;
    @Resource private StateFactory stateFactory;
    @Resource private PhotoFactory photoFactory;

    @Resource private ChameleonQueryManager chameleonQueryManager;
    //@Resource private CountryService countryService;

    public Set<Place> fetchNearPlacesByLocationNameFoursquare(Set<Venue> venues) {
        Set<Place> places = new HashSet<>();
        Map<String, Country> countryMap = new HashMap<>();
        Map<String, City> cityMap = new HashMap<>();
        Map<String, State> stateMap = new HashMap<>();
        for (Venue venue : venues) {
            Place place = placeFactory.create(venue);
            Country country = getCountry(countryMap, venue);
            City city = getCity(cityMap, venue, country);
            Set<Photo> photos = venue.getPhotos();
            for (Photo photo : photos) {
                place.addPhoto(photoFactory.create(photo));
            }
            place.setState(getState(stateMap, venue, city));
            place.setContact(contactFactory.create(venue));
            place.setLocation(locationFactory.create(venue));
            places.add(place);
        }
        return places;
    }

    private City getCity(Map<String, City> cityMap, Venue venue, Country country) {
        City city = cityMap.get(venue.getLocation().getCity());
        if (city == null || !cityMap.containsKey(city.getName())) {
            city = cityFactory.create(venue);
            country.addCity(city);
            cityMap.put(city.getName(), city);
        }
        return city;
    }

    private State getState(Map<String, State> stateMap, Venue venue, City city) {
        State state = stateMap.get(venue.getLocation().getState());
        if (state == null || !stateMap.containsKey(state.getName())) {
            state = stateFactory.create(venue);
            city.addState(state);
            stateMap.put(state.getName(), state);
        }
        return state;
    }

    private Country getCountry(Map<String, Country> countryMap, Venue venue) {
        Country country = countryMap.get(venue.getLocation().getCountry());
        if (country == null || !countryMap.containsKey(country.getName())) {
            country = countryFactory.create(venue);
            countryMap.put(country.getName(), country);
        }
        return country;
    }


}
