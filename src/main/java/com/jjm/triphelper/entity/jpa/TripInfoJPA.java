package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.TripInfo;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Trip_Info")
public class TripInfoJPA implements TripInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="trip_info_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trip_info_seq", sequenceName = "trip_info_seq")
    private Integer id;

    @Column(name = "picture")
    private String picture;

    @Column(name = "place_location", nullable = false, length = 100)
    private String placeLocation;

    @OneToMany(mappedBy = "tripInfo", targetEntity = TripJPA.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Trip> trips;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getPlaceLocation() {
        return placeLocation;
    }

    @Override
    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    @Override
    public String getPicture() {
        return picture;
    }

    @Override
    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public Set<Trip> getTrips() {
        return trips;
    }

    @Override
    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }
}
