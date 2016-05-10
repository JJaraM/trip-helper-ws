package com.jjm.triphelper.entity.spec;

import java.util.Set;

public interface State extends SingularId {
    String getName();
    void setName(String name);

    Set<City> getCities();
    void setCities(Set<City> cities);

    Country getCountry();
    void setCountry(Country country);

    City addCity(City city);
    City removeCity(City city);
}
