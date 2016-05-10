package com.jjm.triphelper.parser;

import com.jjm.foursquare.entity.*;
import com.jjm.foursquare.entity.Photo;
import com.jjm.triphelper.entity.spec.*;
import com.jjm.triphelper.entity.spec.Category;
import com.jjm.triphelper.factory.*;
import com.jjm.triphelper.repository.impl.CityRepositoryJPA;
import com.jjm.triphelper.repository.impl.CountryRepositoryJPA;
import com.jjm.triphelper.repository.impl.StateRepositoryJPA;
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
    @Resource private CategoryFactory categoryFactory;

    @Resource private CountryRepositoryJPA countryRepository;
    @Resource private StateRepositoryJPA stateRepository;
    @Resource private CityRepositoryJPA cityRepository;

    public Set<Place> fetchNearPlacesByLocationNameFoursquare(Set<Venue> venues) {
        Set<Place> places = new HashSet<>();
        Map<String, Category> categoryMap = new HashMap<>();
        Set<String> keys = new HashSet<>();
        venues.forEach( venue -> {
            if (!keys.contains(venue.getReferenceId())) {
                Place place = placeFactory.create(venue);
                Country country = countryRepository.findByName(venue.getLocation().getCountry());
                if (country == null)
                    country = countryRepository.saveEntity(countryFactory.create(venue));
                State state = stateRepository.findByStateNameAndCountryName(venue.getLocation().getState(), country.getName());
                if (state == null) {
                    state = stateFactory.create(venue);
                    country.setStates(new HashSet<>());
                    country.addState(state);
                    state = stateRepository.saveEntity(state);
                }
                City city = cityRepository.findByCityNameAndStateNameAndCountryName(venue.getLocation().getCity(), state.getName(), country.getName());
                if (city == null) {
                    city = cityFactory.create(venue);
                    state.setCities(new HashSet<>());
                    state.addCity(city);
                    city = cityRepository.saveEntity(city);
                }
                Category category = getCategory(categoryMap, venue);
                Set<Photo> photos = venue.getPhotos();
                if (photos != null) {
                    for (Photo photo : photos) {
                        place.addPhoto(photoFactory.create(photo));
                    }
                }
                place.setCity(city);
                place.setContact(contactFactory.create(venue));
                place.setLocation(locationFactory.create(venue));
                place.setCategory(category);
                place.setRating(venue.getRating());
                places.add(place);
                keys.add(venue.getReferenceId());
            }
        });
        return places;
    }

    private Category getCategory(Map<String, Category> categoryMap, Venue venue) {
        Category category = categoryMap.get(venue.getCategory().getName());
        if (category == null || !categoryMap.containsKey(venue.getCategory().getName())) {
            category = categoryFactory.create(venue);
            categoryMap.put(venue.getCategory().getName(), category);
        }
        return category;
    }


}
