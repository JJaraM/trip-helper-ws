package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.jpa.TripJPA;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.TripInfo;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.CacheRepository;

import java.util.Date;

public interface TripRepository extends CacheRepository<TripJPA, Integer> {

    default Trip save(User user, Date startDate, Date endDate, TripInfo tripInfo) {
        TripJPA trip = new TripJPA();
        trip.setOwner(user);
        trip.setTripInfo(tripInfo);
        trip.setEndDate(endDate);
        trip.setStartDate(startDate);
        return save(trip);
    }
}
