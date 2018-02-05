package com.xinwei.service;

import java.util.List;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.shirofunction.ShiroUser;
import com.xinwei.utils.ResponseJson;


public interface ShiroUserService extends EntityService<ShiroUser, Long>{
	
	 public ShiroUser findByUsername(String userName);
	 
	  
	 public List<ShiroUser> findList();
	 

	  public  PaginationResult<ShiroUser>  findAllByPage(Pagination<ShiroUser> pagination);
	  
	  
	  public ResponseJson editUser(ShiroUser shiroUser);
	  
      
	 
	  public ResponseJson editUserRole(String username,String roles);

		 public ResponseJson del(String ids)throws Exception;
	  /**
	   * 用户审核
	   * @param userNameStr
	   * @return
	   */
	  public ResponseJson audit(String userNameStr,String status) throws Exception;
	  public ShiroUser findByUsernameForUpdate(String username);
	  public ResponseJson addUser(ShiroUser shiroUser);
	 
	  public ResponseJson checkNameOrEmallExist(ShiroUser shiroUser);
} 
