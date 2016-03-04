package com.jjm.triphelper.factory;

import com.jjm.foursquare.entity.Photo;
import com.jjm.triphelper.entity.jpa.PhotoJPA;
import org.springframework.stereotype.Component;

@Component
public class PhotoFactory {

    public PhotoJPA create(Photo photo) {
        PhotoJPA photoJPA = new PhotoJPA();
        photoJPA.setReferenceId(photo.getReferenceId());
        photoJPA.setHeight(photo.getHeight());
        photoJPA.setWidth(photo.getWidth());
        photoJPA.setPrefix(photo.getPrefix());
        photoJPA.setSuffix(photo.getSuffix());
        return photoJPA;
    }
}
