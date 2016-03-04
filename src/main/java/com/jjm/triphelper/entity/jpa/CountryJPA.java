package com.jjm.triphelper.entity.jpa;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.foursquare.entity.Location;
import com.jjm.triphelper.entity.spec.City;
import com.jjm.triphelper.entity.spec.Country;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Country")
@Chameleon(type = Location.class)
public class CountryJPA implements Country {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="place_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "place_seq", sequenceName = "place_seq")
    private Integer id;

    @OneToMany(mappedBy = "country", targetEntity = CityJPA.class)
    private Set<City> cities;

    @ChameleonAttr(as = "country") @Column(name = "name", nullable = false) private String name;
    @ChameleonAttr(as = "cc") @Column(name = "code", nullable = false) private String code;

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
    public String code() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
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
    public City addCity(City city) {
        getCities().add(city);
        city.setCountry(this);
        return city;
    }

    @Override
    public City removeCity(City city) {
        getCities().remove(city);
        city.setCountry(null);
        return city;
    }

}
