package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.jpa.PlaceJPA;
import com.jjm.triphelper.repository.CacheRepository;
import com.jjm.triphelper.repository.PlaceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;

public interface PlaceRepositoryJPA extends CacheRepository<PlaceJPA, Integer>, PlaceRepository {

    String FETCH_NEAR_BY_LOCATION = "SELECT p FROM PlaceJPA p " +
            "JOIN p.city c " +
            "JOIN c.state s " +
            "JOIN s.country ct "+
            "JOIN p.category ca " +
            "WHERE s.name LIKE %:state% " +
            "AND c.name LIKE %:city% " +
            "AND ct.name LIKE %:country% ";

    String FETCH_NEAR_BY_LOCATION_AND_CATEGORY_ID  = FETCH_NEAR_BY_LOCATION +
            "AND ca.referenceId = :referenceId ";

    @Query(FETCH_NEAR_BY_LOCATION)
    Set<Place> fetchNearPlacesByLocationName(@Param("city") String city, @Param("state") String state, @Param("country") String country);

    @Query(FETCH_NEAR_BY_LOCATION_AND_CATEGORY_ID)
    Set<Place> fetchNearPlacesByLocationNameAndReferenceId(@Param("city") String city, @Param("state") String state, @Param("country") String country, @Param("referenceId") String referenceId);

    @Query(FETCH_NEAR_BY_LOCATION_AND_CATEGORY_ID)
    Page<Place> fetchByLocationAndCategories(@Param("city") String city, @Param("state") String state, @Param("country") String country, @Param("referenceId") String referenceId, Pageable pageable);

    Place findByReferenceId(String referenceId);

    default Place saveEntity(Place place) {
        save((PlaceJPA) place);
        return place;
    }
}
