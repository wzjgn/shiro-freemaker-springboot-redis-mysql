package com.xinwei.shirofunction;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.service.EntityService;

public interface RoleService extends EntityService<ShiroRole, Long>{
	 
	  public  PaginationResult<ShiroRole>  findAllByPage(Pagination<ShiroRole> pagination);
} 
