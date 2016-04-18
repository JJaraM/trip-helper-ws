package com.jjm.triphelper.entity.dto;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.triphelper.entity.jpa.CategoryJPA;
import java.util.Set;

@Chameleon(type = CategoryJPA.class)
public class CategoryDTO {

    private String name;
    private String referenceId;
    private Set<PlaceDTO> places;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<PlaceDTO> getPlaces() {
        return places;
    }
    public void setPlaces(Set<PlaceDTO> places) {
        this.places = places;
    }

    public String getReferenceId() {
        return referenceId;
    }
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
