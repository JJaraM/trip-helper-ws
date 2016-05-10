package com.jjm.triphelper.entity.jpa;

import com.jjm.triphelper.entity.spec.Country;
import com.jjm.triphelper.entity.spec.State;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Country")
public class CountryJPA implements Country {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="place_seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "place_seq", sequenceName = "place_seq")
    private Integer id;

    @OneToMany(mappedBy = "country", targetEntity = StateJPA.class)
    private Set<State> states;

    @Column(name = "name", nullable = false) private String name;
    @Column(name = "code", nullable = false) private String code;

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
        state.setCountry(this);
        return state;
    }

    @Override
    public State removeState(State state) {
        getStates().remove(state);
        state.setCountry(null);
        return state;
    }


}
