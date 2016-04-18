package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Travel;

import java.util.Date;

public interface TravelService {
    Travel schedule(String tripId, String placeReferenceId, Date startDate, Date endDate);
}
