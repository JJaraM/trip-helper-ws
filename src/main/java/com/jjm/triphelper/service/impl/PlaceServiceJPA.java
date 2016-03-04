package com.jjm.triphelper.service.impl;

import com.google.common.collect.Sets;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.repository.impl.PlaceRepositoryJPA;
import com.jjm.triphelper.service.PlaceService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Set;

@Service(value = "placeServiceJPA")
public class PlaceServiceJPA implements PlaceService {

    @Resource private PlaceRepositoryJPA placeRepositoryJPA;

    @Override
    public Set<Place> fetchNearPlacesByLocationName(String locationName) {
        return Sets.newLinkedHashSet(placeRepositoryJPA.fetchNearPlacesByLocationName(locationName));
    }

    @Override
    public Place save(Place place) {
        return placeRepositoryJPA.saveEntity(place);
    }

}
