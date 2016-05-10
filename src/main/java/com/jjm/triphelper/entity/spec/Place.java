package com.jjm.triphelper.entity.spec;

import java.io.Serializable;
import java.util.Set;

public interface Place extends SingularId,  ReferenceId, Expiration, Serializable {
    String getName();
    void setName(String name);

    String getUrl();
    void setUrl(String url);

    Double getRating();
    void setRating(Double rating);

    Contact getContact();
    void setContact(Contact contact);

    Location getLocation();
    void setLocation(Location location);

    Set<Photo> getPhotos();
    void setPhotos(Set<Photo> photos);

    Photo addPhoto(Photo photo);
    Photo removePhoto(Photo photo);

    City getCity();
    void setCity(City city);

    Set<Travel> getTravels();
    void setTravels(Set<Travel> travels);

    Category getCategory();
    void setCategory(Category category);
}
