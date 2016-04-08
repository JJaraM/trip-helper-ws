package com.jjm.triphelper.entity.spec;

import java.util.Date;
import java.util.Set;

public interface Trip extends SingularId {

    Date getStartDate();
    void setStartDate(Date startDate);

    Date getEndDate();
    void setEndDate(Date endDate);

    User getOwner();
    void setOwner(User owner);

    Set<User> getFriends();
    void setFriends(Set<User> friends);

    User addFriend(User user);
    User removeFriend(User user);

    String getPlaceLocation();
    void setPlaceLocation(String placeLocation);

    Set<Travel> getTravels();
    void setTravels(Set<Travel> travels);




}
