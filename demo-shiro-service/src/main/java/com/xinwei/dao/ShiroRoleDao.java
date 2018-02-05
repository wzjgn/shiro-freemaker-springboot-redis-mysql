package com.xinwei.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.xinwei.shirofunction.ShiroRole;

@Transactional
@Service
public interface ShiroRoleDao  extends BaseDao<ShiroRole, Long> {
   
	 @Query("from ShiroRole") 
	 public List<ShiroRole> findRoleList();
	 
	 
	 public ShiroRole findByRoleName(String roleName);
	 
	  
} 