package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import java.util.Date;

public interface TripService {
    Trip create(User user, String location, Date startDate, Date endDate);
}
