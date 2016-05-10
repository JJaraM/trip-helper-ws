package com.jjm.triphelper.repository.impl;

import com.jjm.triphelper.entity.jpa.CountryJPA;
import com.jjm.triphelper.entity.spec.Country;
import com.jjm.triphelper.repository.CacheRepository;

public interface CountryRepositoryJPA extends CacheRepository<CountryJPA, Integer> {
    Country findByName(String name);

    default Country saveEntity(Country country) {
        save((CountryJPA)country);
        return country;
    }
}
