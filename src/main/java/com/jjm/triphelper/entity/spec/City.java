package com.jjm.triphelper.entity.spec;


import java.util.Set;

public interface City extends SingularId {
    String getName();
    void setName(String name);

    Set<State> getStates();
    void setStates(Set<State> states);

    State addState(State state);
    State removeState(State state);

    Country getCountry();
    void setCountry(Country country);
}
