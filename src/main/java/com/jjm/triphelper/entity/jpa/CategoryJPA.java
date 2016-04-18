package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.Category;
import com.jjm.triphelper.entity.spec.Place;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Category")
public class CategoryJPA implements Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="category_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq")
    private Integer id;

    @Column(name = "reference_id", nullable = false) private String referenceId;
    @Column(name = "name", nullable = false) private String name;

    @OneToMany(mappedBy = "category", targetEntity = PlaceJPA.class)
    private Set<Place> places;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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
    public Set<Place> getPlaces() {
        return places;
    }

    @Override
    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    @Override
    public String getReferenceId() {
        return referenceId;
    }

    @Override
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
