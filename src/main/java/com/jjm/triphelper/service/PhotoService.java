package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Photo;
import java.util.Set;

public interface PhotoService {
    Set<Photo> fetchByPlaceReferenceId(String referenceId);
}