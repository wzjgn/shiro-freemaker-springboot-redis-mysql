package com.xinwei.service;

import java.util.List;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.shirofunction.ShiroRole;


public interface ShiroRoleService extends EntityService<ShiroRole, Long>{
	
	 public ShiroRole findByRoleName(String rolename);
	 
	 public ShiroRole findById(Integer id);

	 public List<ShiroRole> findRoleList();

	  public  PaginationResult<ShiroRole>  findAllByPage(Pagination<ShiroRole> pagination);
	  
} 
