package com.xinwei.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.common.page.SpecificationUtil;
import com.xinwei.dao.ShiroRoleDao;
import com.xinwei.service.ShiroRoleService;
import com.xinwei.shirofunction.ShiroRole;
 


@Service
public class ShiroRoleServiceImpl extends EntityServiceImpl<ShiroRole, Long, ShiroRoleDao> implements ShiroRoleService {
 
	@Override
	 public List<ShiroRole> findRoleList(){
		return entityDao.findRoleList();
	 }
	
	@Override
	 public ShiroRole findByRoleName(String rolename){
		 
		return entityDao.findByRoleName(rolename);
	 }
	
 
	@Override
	 public ShiroRole findById(Integer id){
		 
		return entityDao.findOne(id);
	 }
	
	
	@Override 
	 public  PaginationResult<ShiroRole>  findAllByPage(Pagination<ShiroRole> pagination){

       SpecificationUtil<ShiroRole> specificationUtil = new SpecificationUtil<ShiroRole>();

       Map<String, Object> searchParams = new HashMap<String, Object>();

            
       
       List<String> properties = new ArrayList<String>();
       properties.add("id");
  	    Sort sort = new Sort(Direction.DESC, properties);
  	 
  	    sort= pagination.dataTableOrder(pagination,sort,properties);
      
       
       Specification<ShiroRole> specification = specificationUtil.buildSpecification(searchParams,"and");
  
       
       int page= pagination.getStart()/pagination.getPageSize();
       PageRequest PageRequest = new PageRequest( page,pagination.getPageSize(), sort);
        
       Page<ShiroRole> list = entityDao.findAll(specification, PageRequest);

		 PaginationResult<ShiroRole> pa=new PaginationResult();
		 
		 pa.setData(list.getContent()); 
		 pa.setRecordsFiltered(list.getTotalElements());
		 pa.setRecordsTotal(list.getTotalElements());
		 pa.setDraw(new Integer(pagination.getDraw()));
       return pa;
		
	  }
	
	@Override
	@Autowired
	public void setEntityDao(ShiroRoleDao entityDao) {
		this.entityDao = entityDao;
	}
}
