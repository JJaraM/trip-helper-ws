package com.jjm.triphelper.parser;

import com.jjm.triphelper.entity.spec.Country;

public interface CountryService {
    Country findByCode(String code);
}
