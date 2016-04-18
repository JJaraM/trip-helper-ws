package com.jjm.triphelper.entity.dto;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.triphelper.entity.jpa.TripInfoJPA;

@Chameleon(type = TripInfoJPA.class)
public class TripInfoDTO {

    private String placeLocation;
    private String picture;

    public String getPlaceLocation() {
        return placeLocation;
    }
    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
