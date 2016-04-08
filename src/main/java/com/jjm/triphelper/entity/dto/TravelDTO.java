package com.jjm.triphelper.entity.dto;


import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.triphelper.entity.jpa.TravelJPA;
import java.util.Date;

@Chameleon(type = TravelJPA.class)
public class TravelDTO  {

    private Integer id;
    private Date startDate;
    private Date endDate;
    private PlaceDTO place;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }
}
