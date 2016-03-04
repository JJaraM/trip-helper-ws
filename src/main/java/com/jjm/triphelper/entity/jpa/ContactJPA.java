/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.jpa;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.triphelper.entity.spec.Contact;
import com.jjm.triphelper.entity.spec.Place;

import javax.persistence.*;

/**
 * The {@link ContactJPA} represents the implementation of JPA to {@link Contact}
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
@Entity
@Table(name = "Contact")
@Chameleon(type = com.jjm.foursquare.entity.Contact.class)
public class ContactJPA implements Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="contact_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq")
    @ChameleonAttr
    private Integer id;

    @ChameleonAttr @Column(name = "phone", nullable = true) private String phone;
    @ChameleonAttr @Column(name = "facebook_username", nullable = true) private String facebookUsername;
    @ChameleonAttr @Column(name = "facebook_name", nullable = true) private String facebookName;

    @OneToOne(targetEntity = PlaceJPA.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_place", nullable = false)
    private Place place;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getFacebookUsername() {
        return facebookUsername;
    }

    @Override
    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    @Override
    public String getFacebookName() {
        return facebookName;
    }

    @Override
    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

    @Override
    public Place getPlace() {
        return place;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

}