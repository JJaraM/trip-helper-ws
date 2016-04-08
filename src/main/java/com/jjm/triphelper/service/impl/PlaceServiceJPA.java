package com.jjm.triphelper.service.impl;

import com.google.common.collect.Sets;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.repository.impl.PlaceRepositoryJPA;
import com.jjm.triphelper.service.CryptoService;
import com.jjm.triphelper.service.PlaceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Set;

@Service(value = "placeServiceJPA")
public class PlaceServiceJPA implements PlaceService {

    @Resource private PlaceRepositoryJPA placeRepositoryJPA;
    @Resource private CryptoService cryptoService;

    @Override
    public Set<Place> fetchNearPlacesByLocationName(String locationName) {
        return Sets.newLinkedHashSet(placeRepositoryJPA.fetchNearPlacesByLocationName(locationName));
    }

    @Override
    public Set<Place> fetchNearPlacesByLocationNameAndCategoryId(String locationName, String categoryId) {
        return Sets.newLinkedHashSet(placeRepositoryJPA.fetchNearPlacesByLocationNameAndReferenceId(locationName, categoryId));
    }

    @Override
    public Place save(Place place) {
        return placeRepositoryJPA.saveEntity(place);
    }

    @Override
    public Place findById(String placeId) {
        return placeRepositoryJPA.findOne(Integer.parseInt(cryptoService.decrypt(placeId, "PLACE_ID")));
    }

    @Override
    public Place findByReferenceId(String referenceId) {
        return placeRepositoryJPA.findByReferenceId(referenceId);
    }

}
