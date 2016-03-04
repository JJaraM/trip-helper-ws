package com.jjm.triphelper.entity.jpa;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.triphelper.entity.spec.Location;
import javax.persistence.*;

@Entity
@Table(name = "Location")
@Chameleon(type = com.jjm.foursquare.entity.Location.class)
public class LocationJPA implements Location {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="location_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq")
    private Integer id;

    @ChameleonAttr(as = "lat") @Column(name = "latitude", nullable = false) private Double latitude;
    @ChameleonAttr(as = "lng") @Column(name = "longitude", nullable = false) private Double longitude;

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
}
