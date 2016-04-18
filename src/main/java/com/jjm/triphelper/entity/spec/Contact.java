package com.jjm.triphelper.entity.spec;

public interface Contact extends SingularId {
    String getPhone();
    void setPhone(String phone);

    String getFacebookUsername();
    void setFacebookUsername(String facebookUsername);

    String getFacebookName();
    void setFacebookName(String facebookName);

    Place getPlace();
    void setPlace(Place place);
}
