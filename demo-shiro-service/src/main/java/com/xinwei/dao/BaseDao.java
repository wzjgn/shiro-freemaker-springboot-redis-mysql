package com.xinwei.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseDao<T, pk extends Serializable> extends PagingAndSortingRepository<T, Serializable>,
		JpaSpecificationExecutor<T> {
}
