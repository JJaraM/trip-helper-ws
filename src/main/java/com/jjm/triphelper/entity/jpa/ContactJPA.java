package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.Contact;
import com.jjm.triphelper.entity.spec.Place;

import javax.persistence.*;

@Entity
@Table(name = "Contact")
public class ContactJPA implements Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="contact_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "contact_seq", sequenceName = "contact_seq")
    private Integer id;

    @Column(name = "phone", nullable = true) private String phone;
    @Column(name = "facebook_username", nullable = true) private String facebookUsername;
    @Column(name = "facebook_name", nullable = true) private String facebookName;

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