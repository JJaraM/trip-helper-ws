package com.jjm.triphelper.entity.spec;

public interface Photo extends SingularId, ReferenceId {
    String getPrefix();
    void setPrefix(String prefix);

    String getSuffix();
    void setSuffix(String suffix);

    Double getWidth();
    void setWidth(Double width);

    Double getHeight();
    void setHeight(Double height);

    Place getPlace();
    void setPlace(Place place);
}
