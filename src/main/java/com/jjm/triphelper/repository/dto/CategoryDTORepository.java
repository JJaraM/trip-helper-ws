package com.jjm.triphelper.repository.dto;

import com.jjm.chameleon.annotation.Query;
import com.jjm.triphelper.entity.dto.CategoryDTO;
import com.jjm.triphelper.entity.spec.Category;
import java.util.Set;

public interface CategoryDTORepository {

    @Query("SELECT C.referenceId, C.name, V.referenceId, V.name, P.referenceId, P.prefix, P.suffix, P.width, P.height FROM Category C " +
            "JOIN place V JOIN photos P ")
    Set<CategoryDTO> findAllById(Set<Category> categories);
}
