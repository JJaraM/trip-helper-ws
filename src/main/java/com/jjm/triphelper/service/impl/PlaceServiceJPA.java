package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.repository.impl.PlaceRepositoryJPA;
import com.jjm.triphelper.service.CryptoService;
import com.jjm.triphelper.service.PlaceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service(value = "placeServiceJPA")
public class PlaceServiceJPA implements PlaceService {

    @Resource private PlaceRepositoryJPA placeRepositoryJPA;
    @Resource private CryptoService cryptoService;

    @Override
    public Set<Place> fetchNearPlacesByLocationNameAndCategoryId(String locationName, String categoryId) {
        return placeRepositoryJPA.fetchNearPlacesByLocationNameAndReferenceId(
                LocationFactory.getCity(locationName),
                LocationFactory.getState(locationName),
                LocationFactory.getCountry(locationName),
                categoryId
        );
    }

    @Override
    public Set<Place> fetchNearPlacesByLocationName(String placeLocation) {
        return placeRepositoryJPA.fetchNearPlacesByLocationName(
                LocationFactory.getCity(placeLocation),
                LocationFactory.getState(placeLocation),
                LocationFactory.getCountry(placeLocation)
        );
    }

    @Override
    public Place save(Place place) {
        return placeRepositoryJPA.saveEntity(place);
    }

    @Override
    public Place findById(String placeId) {
        return placeRepositoryJPA.findOne(Integer.parseInt(cryptoService.decrypt(placeId, CryptoService.PLACE_ID)));
    }

    @Override
    public Place findByReferenceId(String referenceId) {
        return placeRepositoryJPA.findByReferenceId(referenceId);
    }

    @Override
    public Place findByLocationAndCategories(String placeLocation, String categoryId) {
        List<Place> places = placeRepositoryJPA.fetchByLocationAndCategories(
                LocationFactory.getCity(placeLocation),
                LocationFactory.getState(placeLocation),
                LocationFactory.getCountry(placeLocation), categoryId, new PageRequest(0, 1)).getContent();
        return places.size() > 0 ? places.get(0) : null;
    }

    static class LocationFactory {

        public static String getCity(String locationName) {
            return locationName.split(",")[0];
        }

        public static String getState(String locationName) {
            return locationName.split(",")[1];
        }

        public static String getCountry(String locationName) {
            return locationName.split(",")[1];
        }

    }

}
