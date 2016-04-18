package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.CategoryJPA;
import com.jjm.triphelper.entity.spec.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

    public Category create(Venue venue) {
        CategoryJPA category = new CategoryJPA();
        category.setName(venue.getCategory().getName());
        category.setReferenceId(venue.getCategory().getId());
        return category;
    }

}
