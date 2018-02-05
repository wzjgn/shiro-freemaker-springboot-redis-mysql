package com.xinwei.shirofunction;

import java.util.List;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.service.EntityService;

public interface FunctionService extends EntityService<Function, Long>{
	 
	  public  PaginationResult<Function>  findAllByPage(Pagination<Function> pagination);
	  public List<Function> findList();
	  
	  public Function findById(Integer id);
	  
	  public Function findByPermissionName(String permissionName);
	  
	  public Function findByPid(Integer pid);
	  

	  public List<Function> findListByPid(Integer pid);
} 
