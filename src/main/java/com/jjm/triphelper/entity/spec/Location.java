package com.jjm.triphelper.entity.spec;

public interface Location extends SingularId {
    Double getLatitude();
    void setLatitude(Double latitude);

    Double getLongitude();
    void setLongitude(Double longitude);

    String getAddress();
    void setAddress(String address);

    String getCrossStreet();
    void setCrossStreet(String crossStreet);
}
