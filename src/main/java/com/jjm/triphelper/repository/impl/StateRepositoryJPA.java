package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.jpa.StateJPA;
import com.jjm.triphelper.entity.spec.State;
import com.jjm.triphelper.repository.CacheRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StateRepositoryJPA extends CacheRepository<StateJPA, Integer> {

    @Query("Select S From StateJPA S JOIN S.country C WHERE S.name = :state AND C.name = :country")
    State findByStateNameAndCountryName(@Param("state") String state, @Param("country") String country);

    default State saveEntity(State state) {
        return save((StateJPA) state);
    }
}
