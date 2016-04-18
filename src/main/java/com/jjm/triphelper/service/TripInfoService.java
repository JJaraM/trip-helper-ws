package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.TripInfo;

public interface TripInfoService {
    TripInfo findByPlaceLocation(String placeLocation);
    TripInfo save(String placeLocation);
}
