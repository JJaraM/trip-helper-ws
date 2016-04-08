package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.repository.impl.TravelRepository;
import com.jjm.triphelper.service.PlaceService;
import com.jjm.triphelper.service.TravelService;
import com.jjm.triphelper.service.TripService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class TravelServiceImpl implements TravelService {

    @Resource private TripService tripService;
    @Resource private PlaceService placeService;
    @Resource private TravelRepository travelRepository;

    @Override
    public Travel schedule(String tripId, String placeId, Date startDate, Date endDate) {
        Trip trip = tripService.findById(tripId);
        Place place = placeService.findByReferenceId(placeId);
        return travelRepository.save(trip, place, startDate, endDate);
    }
}
