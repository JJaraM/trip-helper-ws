package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.jpa.CityJPA;
import com.jjm.triphelper.entity.spec.City;
import com.jjm.triphelper.repository.CacheRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepositoryJPA extends CacheRepository<CityJPA, Integer> {

    @Query("SELECT City From CityJPA City " +
            "JOIN City.state State " +
            "JOIN State.country Country " +
            "WHERE City.name = :city " +
            "AND State.name = :state " +
            "AND Country.name = :country ")
    City findByCityNameAndStateNameAndCountryName(@Param("city") String city,
                                                  @Param("state") String state,
                                                  @Param("country") String country);

    default City saveEntity(City city) {
        return save((CityJPA)city);
    }
}
