package com.jjm.triphelper.entity.jpa;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.foursquare.entity.Location;
import com.jjm.triphelper.entity.spec.City;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.spec.State;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "State")
@Chameleon(type = Location.class)
public class StateJPA implements State {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="state_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "state_seq", sequenceName = "state_seq")
    private Integer id;

    @Column(name = "name", nullable = true) private String name;

    @ManyToOne(targetEntity = CityJPA.class, cascade = CascadeType.ALL) @JoinColumn(name="id_city")
    private City city;

    @OneToMany(mappedBy = "state", targetEntity = PlaceJPA.class)
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
    public City getCity() {
        return city;
    }

    @Override
    public void setCity(City city) {
        this.city = city;
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
    public Place addPlace(Place place) {
        getPlaces().add(place);
        place.setState(this);
        return place;
    }

    @Override
    public Place removePlace(Place place) {
        getPlaces().remove(place);
        place.setState(null);
        return place;
    }


}
