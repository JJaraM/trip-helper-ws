package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Travel;

import java.util.Date;

public interface TravelService {
    Travel schedule(String tripId, String placeId, Date startDate, Date endDate);
}
