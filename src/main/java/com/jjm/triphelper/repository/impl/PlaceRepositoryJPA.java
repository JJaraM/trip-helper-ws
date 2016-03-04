package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.jpa.PlaceJPA;
import com.jjm.triphelper.repository.CacheRepository;
import com.jjm.triphelper.repository.PlaceRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PlaceRepositoryJPA extends CacheRepository<PlaceJPA, Integer>, PlaceRepository {

    String FETCH_NEAR_BY_LOCATION = "SELECT p FROM PlaceJPA p " +
                                    "JOIN p.state s " +
                                    "JOIN s.city c " +
                                    "JOIN c.country ct "+
                                    "WHERE s.name = :location OR " +
                                    "c.name = :location OR " +
                                    "ct.name = :location ";

    @Override
    @Query(FETCH_NEAR_BY_LOCATION)
    Set<Place> fetchNearPlacesByLocationName(@Param("location") String location);

    default Place saveEntity(Place place) {
        save((PlaceJPA) place);
        return place;
    }
}
