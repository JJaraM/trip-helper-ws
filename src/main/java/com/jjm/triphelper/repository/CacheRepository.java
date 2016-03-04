package com.jjm.triphelper.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CacheRepository<T, ID extends Serializable> extends CrudRepository<T, ID>, QueryDslPredicateExecutor<T> {
}
