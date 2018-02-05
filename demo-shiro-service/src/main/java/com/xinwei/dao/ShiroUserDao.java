package com.xinwei.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.xinwei.shirofunction.ShiroUser;

@Service
public interface ShiroUserDao  extends BaseDao<ShiroUser, Long> {
  public ShiroUser findByUsername(String userName);
   

  @Query(value = "select j from ShiroUser j where j.username = :username ")
  public ShiroUser findByUsernameForUpdate(@Param("username") String username);
  
  // 动态sql分页查询
  Page<ShiroUser> findAll(Specification<ShiroUser> spec, Pageable pageable);
} 