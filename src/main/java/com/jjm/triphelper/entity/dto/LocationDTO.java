package com.jjm.triphelper.entity.dto;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.triphelper.entity.jpa.LocationJPA;
import com.jjm.triphelper.entity.spec.Location;

@Chameleon(type = LocationJPA.class)
public class LocationDTO {

    private Double latitude;
    private Double longitude;
    private String address;
    private String crossStreet;

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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }
    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }


}
