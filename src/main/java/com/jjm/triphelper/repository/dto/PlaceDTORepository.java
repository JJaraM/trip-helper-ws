package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Datasource;
import com.jjm.chameleon.annotation.Query;
import com.jjm.triphelper.entity.dto.PlaceDTO;
import com.jjm.triphelper.entity.spec.Place;
import java.util.Set;

public interface PlaceDTORepository {

    @Query("SELECT V.referenceId, V.name, V.rating, P.referenceId, P.prefix, P.suffix, P.width, P.height, L.crossStreet, L.address FROM Place V JOIN photos P JOIN location L ")
    Set<PlaceDTO> fetchNearPlacesByLocationName(@Datasource Set<Place> source);

    @Query("SELECT V.referenceId, V.name, P.referenceId, P.prefix, P.suffix, P.width, P.height, C.name, C.referenceId FROM Place V JOIN photos P JOIN category C ")
    Set<PlaceDTO> fetchNearPlacesByLocationNameAndCategory(@Datasource Set<Place> source);
}
