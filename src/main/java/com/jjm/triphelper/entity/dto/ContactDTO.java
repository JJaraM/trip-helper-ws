package com.jjm.triphelper.entity.dto;

import com.jjm.chameleon.annotation.Chameleon;
import com.jjm.chameleon.annotation.ChameleonAttr;
import com.jjm.triphelper.entity.jpa.ContactJPA;

@Chameleon(type = ContactJPA.class)
public class ContactDTO {

    @ChameleonAttr private Integer id;
    @ChameleonAttr private String phone;
    @ChameleonAttr private String facebookUsername;
    @ChameleonAttr private String facebookName;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }
    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public String getFacebookName() {
        return facebookName;
    }
    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }

}
