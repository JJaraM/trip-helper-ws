package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.City;
import com.jjm.triphelper.entity.spec.Country;
import com.jjm.triphelper.entity.spec.State;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "City")
public class CityJPA implements City {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="city_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "city_seq", sequenceName = "city_seq")
    private Integer id;

    @OneToMany(mappedBy = "city", targetEntity = StateJPA.class)
    private Set<State> states;

    @ManyToOne(targetEntity = CountryJPA.class, cascade = CascadeType.ALL) @JoinColumn(name="id_country")
    private Country country;

    @Column(name = "name", nullable = true) private String name;

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
    public Set<State> getStates() {
        return states;
    }

    @Override
    public void setStates(Set<State> states) {
        this.states = states;
    }

    @Override
    public State addState(State state) {
        getStates().add(state);
        state.setCity(this);
        return state;
    }

    @Override
    public State removeState(State state) {
        getStates().remove(state);
        state.setCity(null);
        return state;
    }

    @Override
    public Country getCountry() {
        return country;
    }

    @Override
    public void setCountry(Country country) {
        this.country = country;
    }


}
