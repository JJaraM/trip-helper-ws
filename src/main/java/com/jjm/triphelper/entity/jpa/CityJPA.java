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

    @ManyToOne(targetEntity = StateJPA.class, cascade = CascadeType.ALL)
    @JoinColumn(name="id_state")
    private State state;

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
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }




}
