/*
 * Copyright (c) 2016, 2020, JJM and/or its affiliates. All rights reserved.
 * JJM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.jjm.triphelper.entity.spec;

import java.util.Date;

/**
 * The {@link Expiration} represents the expiration of the record to be deleted
 * @author Jonathan Jara Morales
 * @since TRIP-1.0
 */
public interface Expiration {

    /**
     * Identifies when the records was created
     * @return
     */
    Date getCreatedDate();

    /**
     * Specifies the time when the records was created
     * @param createdDate
     */
    void setCreatedDate(Date createdDate);
}
