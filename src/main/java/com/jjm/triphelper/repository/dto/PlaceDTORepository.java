package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Datasource;
import com.jjm.chameleon.annotation.Query;
import com.jjm.triphelper.entity.dto.PlaceDTO;
import com.jjm.triphelper.entity.spec.Place;
import org.springframework.data.repository.query.Param;
import java.util.Set;

public interface PlaceDTORepository {

    @Query("SELECT V.referenceId, V.name, P.referenceId, P.prefix, P.suffix, P.width, P.height FROM Place V JOIN Photos P ")
    Set<PlaceDTO> fetchNearPlacesByLocationName(@Datasource Set<Place> source);
}
