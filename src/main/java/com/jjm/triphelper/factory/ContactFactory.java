package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Venue;
import com.jjm.triphelper.entity.jpa.ContactJPA;
import org.springframework.stereotype.Component;

@Component
public class ContactFactory {

    public ContactJPA create(Venue venue) {
        ContactJPA contact = new ContactJPA();
        contact.setFacebookName(venue.getContact().getFacebookName());
        contact.setFacebookUsername(venue.getContact().getFacebookUsername());
        contact.setPhone(venue.getContact().getPhone());
        return contact;
    }
}
