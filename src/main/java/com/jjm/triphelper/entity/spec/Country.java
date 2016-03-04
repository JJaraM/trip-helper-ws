package com.jjm.triphelper.entity.spec;

import java.util.Set;

public interface Country extends SingularId {

    String getName();
    void setName(String name);

    String code();
    void setCode(String code);

    Set<City> getCities();
    void setCities(Set<City> cities);

    City addCity(City city);
    City removeCity(City city);
}
