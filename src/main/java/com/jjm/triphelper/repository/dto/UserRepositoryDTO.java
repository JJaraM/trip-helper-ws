package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Datasource;
import com.jjm.chameleon.annotation.Query;
import com.jjm.triphelper.entity.dto.UserDTO;
import com.jjm.triphelper.entity.spec.User;

public interface UserRepositoryDTO {

    @Query("SELECT U.id, U.username FROM User U")
    UserDTO find(@Datasource User user);
}
