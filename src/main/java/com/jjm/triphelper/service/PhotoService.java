/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.service;

import com.jjm.triphelper.entity.spec.Photo;

import java.util.Set;

/**
 * The {@code PhotoService} represents the specification of photo services
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface PhotoService {
    Set<Photo> fetchByPlaceReferenceId(String referenceId);
}