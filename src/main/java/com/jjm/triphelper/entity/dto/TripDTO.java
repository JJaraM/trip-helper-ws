package com.jjm.triphelper.entity.dto;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.Serializer;
import com.jjm.triphelper.entity.jpa.TripJPA;
import com.jjm.triphelper.entity.jpa.interceptor.TripInterceptor;
import java.util.Date;

@Chameleon(type = TripJPA.class)
public class TripDTO {

    @Serializer(value = TripInterceptor.class) private String id;
    private Date startDate;
    private Date endDate;
    private String placeLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

}
