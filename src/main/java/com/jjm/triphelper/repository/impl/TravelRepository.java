package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.jpa.TravelJPA;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.repository.CacheRepository;

import java.util.Date;

public interface TravelRepository extends CacheRepository<TravelJPA, Integer> {

    default Travel save(Trip trip, Place place, Date startDate, Date endDate) {
        TravelJPA travel = new TravelJPA();
        travel.setTrip(trip);
        travel.setPlace(place);
        travel.setStartDate(startDate);
        travel.setEndDate(endDate);
        save(travel);
        return travel;
    }
}
