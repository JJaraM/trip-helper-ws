package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Datasource;
import com.jjm.chameleon.annotation.Query;
import com.jjm.triphelper.entity.dto.TripDTO;
import com.jjm.triphelper.entity.spec.Trip;

public interface TripRepositoryDTO {

    @Query("SELECT T.id FROM Trip T ")
    TripDTO find(@Datasource Trip trip);
}
