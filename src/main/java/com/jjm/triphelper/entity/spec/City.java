package com.jjm.triphelper.entity.spec;

public interface City extends SingularId {
    String getName();
    void setName(String name);

    State getState();
    void setState(State state);
}
