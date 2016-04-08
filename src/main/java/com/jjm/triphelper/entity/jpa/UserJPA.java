/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.jpa;

import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.chameleon.annotation.jpa.ChameleonStrategy;
import com.jjm.triphelper.entity.spec.Photo;
import com.jjm.triphelper.entity.spec.Trip;
import com.jjm.triphelper.entity.spec.User;
import javax.persistence.*;
import java.util.Set;

/**
 * The {@link UserJPA} represents the implementation of JPA to {@link User}
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
@Entity
@Table(name = "Uzer")
public class UserJPA implements User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="user_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
    private Integer id;

    @Column(name = "name", nullable = false, length = 200, unique = true) private String name;
    @Column(name = "email", nullable = false, length = 200, unique = true) private String email;
    @Column(name = "password", nullable = false, length = 200) private String password;

    @OneToMany(mappedBy = "owner", targetEntity = TripJPA.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Trip> trips;

    @ManyToMany(targetEntity = TripJPA.class) @JoinTable(name = "user_trip",  joinColumns = {
            @JoinColumn(name = "uzer_id", referencedColumnName="id") },
            inverseJoinColumns = { @JoinColumn(name = "trip_id", referencedColumnName="id") })
    private Set<Trip> shareTrips;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<Trip> getTrips() {
        return trips;
    }

    @Override
    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public Trip addTrip(Trip trip) {
        getTrips().add(trip);
        trip.setOwner(this);
        return trip;
    }

    @Override
    public Trip removeTrip(Trip trip) {
        getTrips().remove(trip);
        trip.setOwner(null);
        return trip;
    }

    @Override
    public Set<Trip> getShareTrips() {
        return shareTrips;
    }

    @Override
    public void setShareTrips(Set<Trip> shareTrips) {
        this.shareTrips = shareTrips;
    }
}
