package com.jjm.triphelper.entity.jpa;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.spec.Photo;
import com.jjm.triphelper.entity.spec.Place;

import javax.persistence.*;

@Entity
@Table(name = "Photo")
@Chameleon(type = com.jjm.foursquare.entity.Photo.class)
public class PhotoJPA implements Photo {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="photo_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "photo_seq", sequenceName = "photo_seq")
    private Integer id;

    @ChameleonAttr @Column(name = "reference_id", nullable = false) private String referenceId;
    @ChameleonAttr @Column(name = "prefix", nullable = true) private String prefix;
    @ChameleonAttr @Column(name = "suffix", nullable = true) private String suffix;
    @ChameleonAttr @Column(name = "width", nullable = true) private Double width;
    @ChameleonAttr @Column(name = "height", nullable = true) private Double height;

    @ManyToOne(targetEntity = PlaceJPA.class, cascade = CascadeType.ALL) @JoinColumn(name="id_place") private Place place;

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
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public Double getWidth() {
        return width;
    }

    @Override
    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    public Double getHeight() {
        return height;
    }

    @Override
    public void setHeight(Double height) {
        this.height = height;
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
