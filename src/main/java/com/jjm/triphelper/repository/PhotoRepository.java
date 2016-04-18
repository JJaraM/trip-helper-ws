package com.jjm.triphelper.repository;

import com.jjm.triphelper.entity.spec.Photo;
import java.util.Set;

public interface PhotoRepository {
    Set<Photo> fetchAllByReferenceId(String referenceId);
}
