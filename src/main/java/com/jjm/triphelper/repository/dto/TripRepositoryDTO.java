package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Datasource;
import com.jjm.chameleon.annotation.Query;
import com.jjm.triphelper.entity.dto.TravelDTO;
import com.jjm.triphelper.entity.dto.TripDTO;
import com.jjm.triphelper.entity.spec.Travel;
import com.jjm.triphelper.entity.spec.Trip;

import java.util.Set;

public interface TripRepositoryDTO {

    @Query("SELECT T.id FROM Trip T ")
    TripDTO find(@Datasource Trip trip);

    @Query("SELECT T.id, T.startDate, T.placeLocation FROM Trip T ")
    Set<TripDTO> findAll(@Datasource Set<Trip> trips);
}
