package com.jjm.triphelper.repository;

import com.jjm.triphelper.entity.spec.Photo;

import java.util.Set;

/**
 * Created by jjara on 2/9/2016.
 */
public interface PhotoRepository {

    Set<Photo> fetchAllByReferenceId(String referenceId);
}
