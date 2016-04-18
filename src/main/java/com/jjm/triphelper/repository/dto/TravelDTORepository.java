package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Datasource;
import com.jjm.chameleon.annotation.Query;
import com.jjm.triphelper.entity.dto.TravelDTO;
import com.jjm.triphelper.entity.spec.Travel;
import java.util.Set;

public interface TravelDTORepository {

    @Query("SELECT T.id, T.startDate, T.endDate, P.name, Ph.prefix, Ph.suffix, Ph.width, Ph.height FROM Travel T JOIN place P JOIN photos Ph")
    Set<TravelDTO> findAll(@Datasource Set<Travel> travels);

    @Query("SELECT T.id, T.startDate, T.endDate, P.name FROM Travel T JOIN place P ")
    TravelDTO schedulePlace(@Datasource Travel schedule);
}