package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.impl.TripRepository;
import com.jjm.triphelper.service.CryptoService;
import com.jjm.triphelper.service.PlaceService;
import com.jjm.triphelper.service.TripService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class TripServiceImpl implements TripService {

    @Resource private TripRepository tripRepository;
    @Resource private CryptoService cryptoService;
    @Resource private PlaceService placeService;

    @Override
    public Trip create(User user, String location, Date startDate, Date endDate) {
        Trip trip = tripRepository.save(user, location, startDate, endDate);
        placeService.fetchNearPlacesByLocationName(trip.getPlaceLocation());
        return trip;
    }

    @Override
    public Trip findById(String tripId) {
        return tripRepository.findOne(Integer.parseInt(cryptoService.decrypt(tripId, "TRIP_ID")));
    }
}
