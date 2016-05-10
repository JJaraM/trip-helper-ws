package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.City;
import com.jjm.triphelper.entity.spec.Country;
import com.jjm.triphelper.entity.spec.Place;
import com.jjm.triphelper.entity.spec.State;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "State")
public class StateJPA implements State {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="state_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "state_seq", sequenceName = "state_seq")
    private Integer id;

    @Column(name = "name", nullable = true) private String name;

    @OneToMany(mappedBy = "state", targetEntity = CityJPA.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<City> cities;

    @ManyToOne(targetEntity = CountryJPA.class, cascade = CascadeType.ALL)
    @JoinColumn(name="id_country")
    private Country country;

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
    public Set<City> getCities() {
        return cities;
    }

    @Override
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public City addCity(City city) {
        getCities().add(city);
        city.setState(this);
        return city;
    }

    @Override
    public City removeCity(City city) {
        getCities().remove(city);
        city.setState(null);
        return city;
    }


}
