package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.Trip;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Travel")
public class TravelJPA implements Travel {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="travel_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "travel_seq", sequenceName = "travel_seq")
    private Integer id;

    @ManyToOne(targetEntity = TripJPA.class, cascade = CascadeType.ALL) @JoinColumn(name="id_travel")
    private Trip trip;

    @ManyToOne(targetEntity = PlaceJPA.class, cascade = CascadeType.ALL) @JoinColumn(name="id_place")
    private Place place;

    @Column(name="start_date", nullable = false) @Temporal(TemporalType.TIMESTAMP) private Date startDate;
    @Column(name="end_date", nullable = false) @Temporal(TemporalType.TIMESTAMP) private Date endDate;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public Place getPlace() {
        return place;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public Trip getTrip() {
        return trip;
    }

    @Override
    public void setTrip(Trip trip) {
        this.trip = trip;
    }


}
