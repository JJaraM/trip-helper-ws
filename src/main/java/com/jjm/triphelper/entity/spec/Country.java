package com.jjm.triphelper.entity.spec;

import java.util.Set;

public interface Country extends SingularId {
    String getName();
    void setName(String name);

    String code();
    void setCode(String code);

    Set<State> getStates();
    void setStates(Set<State> states);

    State addState(State state);
    State removeState(State state);

}
