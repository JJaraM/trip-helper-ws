package com.jjm.triphelper.service.impl;

import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import com.jjm.triphelper.repository.impl.TripRepository;
import com.jjm.triphelper.service.TripService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class TripServiceImpl implements TripService {

    @Resource private TripRepository tripRepository;

    @Override
    public Trip create(User user, String location, Date startDate, Date endDate) {
        return tripRepository.save(user, location, startDate, endDate);
    }
}
