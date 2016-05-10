package com.jjm.triphelper.repository.jpa;

import com.jjm.triphelper.controller.AbstractIntegrationTest;
import com.jjm.triphelper.entity.jpa.*;
import com.jjm.triphelper.entity.spec.*;
import com.jjm.triphelper.repository.impl.PlaceRepositoryJPA;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PlaceRepositoryJPATest extends AbstractIntegrationTest {

    @Resource private PlaceRepositoryJPA placeRepositoryJPA;
    private static boolean setUpIsDone = false;

    @Before
    public void setUp() {
        if (setUpIsDone) {
            return;
        }
        Category category1 = new CategoryJPA();
        category1.setName("category1");
        category1.setReferenceId("referenceId1");

        Category category2 = new CategoryJPA();
        category2.setName("category2");
        category2.setReferenceId("referenceId2");

        State state1 = new StateJPA();
        state1.setName("state1");

        /*City city1 = new CityJPA();
        city1.setName("city1");
        city1.setStates(new HashSet<>());
        city1.addState(state1);

        Country country = new CountryJPA();
        country.setName("country1");
        country.setCode("CO1");
        country.setCities(new HashSet<>());
        country.addCity(city1);

        PlaceJPA place1 = new PlaceJPA();
        place1.setName("place1");
        place1.setCreatedDate(new Date());
        place1.setReferenceId("reference1");
        place1.setState(state1);
        place1.setCategory(category1);

        PlaceJPA place2 = new PlaceJPA();
        place2.setName("place2");
        place2.setCreatedDate(new Date());
        place2.setReferenceId("reference2");
        place2.setState(state1);
        place2.setCategory(category2);
        placeRepositoryJPA.save(Arrays.asList(place1, place2));*/
        setUpIsDone = true;
    }

    @Test
    public void fetchNearPlacesByLocationName() {
        Set<Place> places = placeRepositoryJPA.fetchNearPlacesByLocationName("city1", "state1", "country1");
        Assert.assertEquals(2, places.size());
        Assert.assertTrue(contains(places, "reference1"));
        Assert.assertTrue(contains(places, "reference2"));
    }

    @Test
    public void fetchNearPlacesByLocationNameAndReferenceId() {
        Set<Place> places = placeRepositoryJPA.fetchNearPlacesByLocationNameAndReferenceId("city1", "state1", "country1","referenceId1");
        Assert.assertEquals(1, places.size());
        Assert.assertTrue(contains(places, "reference1"));
    }

    @Test
    public void findByReferenceId() {
        Assert.assertEquals("reference1", placeRepositoryJPA.findByReferenceId("reference1").getReferenceId());
    }

    private boolean contains(Set<Place> places, String referenceId) {
        for (Place place : places) {
            if (place.getReferenceId().equals(referenceId)) {
                return true;
            }
        }
        return false;
    }


}
