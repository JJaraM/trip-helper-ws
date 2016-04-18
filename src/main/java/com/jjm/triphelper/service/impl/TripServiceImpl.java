package com.jjm.triphelper.service.impl;

import com.jjm.foursquare.entity.Response;
import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.TripInfoJPA;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.TripInfo;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.impl.TripRepository;
import com.jjm.triphelper.service.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

@Service
public class TripServiceImpl implements TripService {

    @Resource private TripRepository tripRepository;
    @Resource private CryptoService cryptoService;
    @Resource private VenueService venueService;
    @Resource private TripInfoService tripInfoService;

    @Override
    public Trip create(User user, String location, Date startDate, Date endDate) {
        Response<Venue> venues = venueService.fetchNearVenuesByLocationName(location);
        TripInfo tripInfo = tripInfoService.findByPlaceLocation(venues.getGeocode().getDisplayString());
        Trip trip = null;
        if (tripInfo == null) {
            tripInfo = tripInfoService.save(venues.getGeocode().getDisplayString());
            venueService.savePlaces(venues);
        }
        if (tripInfo != null) {
            trip = tripRepository.save(user, startDate, endDate, tripInfo);
        }
        return trip;
    }

    @Override
    public Trip findById(String tripId) {
        return tripRepository.findOne(Integer.parseInt(cryptoService.decrypt(tripId, CryptoService.TRIP_ID)));
    }
}
