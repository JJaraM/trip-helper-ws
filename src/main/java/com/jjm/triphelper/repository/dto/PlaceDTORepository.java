package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Query;
import com.jjm.chameleon.annotation.Repository;
import com.jjm.triphelper.entity.dto.PlaceDTO;
import com.jjm.triphelper.entity.spec.Place;
import java.util.Set;

@Repository
public interface PlaceDTORepository {

    @Query("SELECT V.referenceId, V.name, P.referenceId, P.prefix, P.suffix, P.width, P.height FROM Place V JOIN Photos P")
    Set<PlaceDTO> fetchNearPlacesByLocationName(Set<Place> source);
}
