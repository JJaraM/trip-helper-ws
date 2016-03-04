package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.StateJPA;
import org.springframework.stereotype.Component;

@Component
public class StateFactory {

    public StateJPA create(Venue venue) {
        StateJPA state = new StateJPA();
        state.setName(venue.getLocation().getState());
        return state;
    }
}
