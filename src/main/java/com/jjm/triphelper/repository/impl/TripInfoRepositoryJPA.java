package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.jpa.TripInfoJPA;
import com.jjm.triphelper.entity.spec.TripInfo;
import com.jjm.triphelper.repository.CacheRepository;

public interface TripInfoRepositoryJPA extends CacheRepository<TripInfoJPA, Integer> {

    TripInfo findByPlaceLocation(String placeLocation);

    default TripInfo save(String placeLocation) {
        TripInfoJPA tripInfo = new TripInfoJPA();
        tripInfo.setPlaceLocation(placeLocation);
        save(tripInfo);
        return tripInfo;
    }
}
