package com.jjm.triphelper.entity.jpa;

import com.jjm.chameleon.annotation.jpa.ChameleonStrategy;
import com.jjm.foursquare.entity.Venue;
import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.triphelper.entity.spec.*;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "Place")
@Chameleon(type = Venue.class)
public class PlaceJPA implements Place {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="place_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "place_seq", sequenceName = "place_seq")
    @ChameleonAttr
    private Integer id;

    @ChameleonAttr @Column(name = "reference_id", nullable = false) private String referenceId;
    @ChameleonAttr @Column(name="created_date", nullable = false) @Temporal(TemporalType.TIMESTAMP) private Date createdDate;

    @ChameleonAttr @Column(name = "name", nullable = false) private String name;
    @ChameleonAttr @Column(name = "url", nullable = true) private String url;
    @ChameleonAttr @Column(name = "rating", nullable = true) private Double rating;

    @OneToOne(targetEntity = ContactJPA.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_contact", nullable = true)
    @ChameleonAttr(type = ContactJPA.class, stretegy = ChameleonStrategy.SET)
    private Contact contact;

    @OneToMany(mappedBy = "place", targetEntity = PhotoJPA.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ChameleonAttr(type = PhotoJPA.class, stretegy = ChameleonStrategy.ADD)
    private Set<Photo> photos;

    @ManyToOne(targetEntity = StateJPA.class, cascade = CascadeType.ALL) @JoinColumn(name="id_state")
    @ChameleonAttr
    private State state;

    @OneToOne(targetEntity = LocationJPA.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_location", nullable = true)
    @ChameleonAttr(type = LocationJPA.class)
    private Location location;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getReferenceId() {
        return referenceId;
    }

    @Override
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
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
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Double getRating() {
        return rating;
    }

    @Override
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public void setContact(Contact contact) {
        contact.setPlace(this);
        this.contact = contact;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Set<Photo> getPhotos() {
        return photos;
    }

    @Override
    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public Photo addPhoto(Photo photo) {
        photo.setPlace(this);
        getPhotos().add(photo);
        return photo;
    }

    @Override
    public Photo removePhoto(Photo photo) {
        photo.setPlace(null);
        getPhotos().remove(photo);
        return photo;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
