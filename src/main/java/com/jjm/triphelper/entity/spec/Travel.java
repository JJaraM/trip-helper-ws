package com.jjm.triphelper.entity.spec;


import java.util.Date;

public interface Travel extends SingularId {
    Date getStartDate();
    void setStartDate(Date startDate);

    Date getEndDate();
    void setEndDate(Date endDate);

    Place getPlace();
    void setPlace(Place place);

    Trip getTrip();
    void setTrip(Trip trip);
}
