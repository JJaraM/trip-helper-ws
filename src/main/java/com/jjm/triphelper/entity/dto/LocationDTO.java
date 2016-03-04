package com.jjm.triphelper.entity.dto;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.triphelper.entity.jpa.LocationJPA;

@Chameleon(type = LocationJPA.class)
public class LocationDTO {

    @ChameleonAttr(as = "lat") private Double latitude;
    @ChameleonAttr(as = "lng") private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
