package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.Location;
import javax.persistence.*;

@Entity
@Table(name = "Location")
public class LocationJPA implements Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="location_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq")
    private Integer id;

    @Column(name = "latitude", nullable = false) private Double latitude;
    @Column(name = "longitude", nullable = false) private Double longitude;
    @Column(name = "crossStreet") private String crossStreet;
    @Column(name = "address") private String address;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCrossStreet() {
        return crossStreet;
    }

    @Override
    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }
}
