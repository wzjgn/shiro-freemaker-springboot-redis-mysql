package com.xinwei.common.page;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageInfo;

public class Pagination<T> extends PageInfo<T> {

	

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	public Pagination() {
	}

	public Pagination(List<T> list) {
		super(list);
	}

	private int draw;
	private Search search;
	private List<Order> order;
	private List<Columns> columns;
	
	
	
	 
	//begin.................................
	private String extra_search;
	public String getExtra_search() {
		return extra_search;
	}

	public void setExtra_search(String extra_search) {
		this.extra_search = extra_search;
	}
	
	//将查询条件转换到实体类对应的字段中.
	public static <T> T queryCondtion2Entry(T t, Pagination<T> page) {
		String seachstring = page.getExtra_search();
		
		
		//判断是否为空,如果为空直接返回
		if(null==seachstring){
			return t;
		}
		if("".equals(seachstring.trim())){
			return t;
		}
		
		//这里添加一下 编码解码 看看问题.
	    try {
	    	  seachstring = java.net.URLDecoder.decode(seachstring, "utf-8");
		} catch (Exception e) {
		       
		}
		//这里添加一下 编码解码 看看问题.
		
		//max=1&min=0
		String[] split = seachstring.split("&");
		
		for (String query : split) {
			if (query!=null) {
				String[] keyAndValue = query.split("=");
				if(keyAndValue!=null){
					if(keyAndValue.length==1){
						continue;
					}
					//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

					Field field = ReflectionUtils.findField(t.getClass(), keyAndValue[0]);
					// 修改访问控制权限
					boolean accessFlag = field.isAccessible();
					field.setAccessible(true);
					if (null != field) {
                        //暂时添加  Integer 和 String 的类型判断.
						String value =keyAndValue[1];
						if (field.getType() == Integer.class) {

							
							try {
								Integer.parseInt(value);
								ReflectionUtils.setField(field, t, Integer.parseInt(value));
							} catch (NumberFormatException e) {
							}
						
						} else if (field.getType() == Date.class) {
							// 如果字段是时间类型
						} else {
							ReflectionUtils.setField(field, t, value);
						}
					}
					// 恢复访问控制权限
					field.setAccessible(accessFlag);
					//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
				}
			}
		}
		return t;
	}
	
	
	//end.....................................

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return super.getStartRow();
	}

	public void setStart(int start) {
		super.setStartRow(start);
	}

	public int getLength() {
		return super.getPageSize();
	}

	public void setLength(int length) {
		super.setPageSize(length);
	}

	public int getRecordsTotal() {
		return (int) super.getTotal();
	}

	public int getRecordsFiltered() {
		return (int) super.getTotal();
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<T> getData() {
		return super.getList();
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public void setColumns(List<Columns> columns) {
		this.columns = columns;
	}

	public List<Order> getOrder() {
		return order;
	}

	public Search getSearch() {
		return search;
	}

	public List<Columns> getColumns() {
		return columns;
	}

	public static <T> T dataTable2Entry(T t, Pagination<T> page) {
		List<Order> odrs = new ArrayList<Order>();
		List<Columns> cols = page.getColumns();
		if (null != page.getOrder())
			for (Order odr : page.getOrder()) {
				String cname = cols.get(odr.getColumn()).getData();
				if (null != cname && !"".equals(cname)) {
					Order o = new Order();
					o.setDir(cname + "&&" + odr.getDir()); // result: devicename
															// desc
					o.setColumn(odr.getColumn());
					odrs.add(o);
				}
			}
		page.setOrder(odrs);
		if (null != cols)
			for (Columns col : cols) {
				String cname = col.getData();// columsname字段名
				// col.isSearchable();// 是否搜索
				if (!StringUtils.isEmpty(cname) && col.isSearchable()) {
					Field field = ReflectionUtils.findField(t.getClass(), cname);
					field.setAccessible(true);
					if (null != field) {

						if (field.getType() == Integer.class) {
							if (!"".equals(page.getSearch().getValue())) {
								String value = page.getSearch().getValue();
								try {
									Integer.parseInt(value);
									ReflectionUtils.setField(field, t, Integer.parseInt(value));
								} catch (NumberFormatException e) {
								}
							}
						} else if (field.getType() == Date.class) {
							// 如果字段是时间类型
						} else {
							ReflectionUtils.setField(field, t, page.getSearch().getValue());
						}
					}
				}
			}
		return t;
	}

	public static <T> Sort dataTableOrder(Pagination<T> page,Sort sort,  List<String> properties) {
		 
		String orderBy = "";
/*		for (int i = 0; null != page.getOrder() && i < page.getOrder().size(); i++) {
			Order order = page.getOrder().get(i);
			orderBy += order.getDir();
			if (i < page.getOrder().size() - 1) {
				orderBy += ",";
			}
		}*/
	 
		for (int i = 0; null != page.getOrder() && i < page.getOrder().size(); i++) {
			Order order = page.getOrder().get(i);
			orderBy  = order.getDir();
			
			 if(orderBy!=null&&!orderBy.equals("")){

		            String orderbystr = orderBy.split("&&")[0];
		            System.out.println("orderbystr="+orderbystr);
		            String sortStr = orderBy.split("&&")[1];
		            properties.clear();
		            properties.add(orderbystr);
		            
		            sort = new Sort(Direction.fromString(sortStr), properties);
			 }
		 
		}
		
		return sort;
	}
	
	//begin ...........................................................
	public static <T> Sort getOrderBydataTable(Pagination<T> page) {
		//Sort sort=new Sort();
		List<org.springframework.data.domain.Sort.Order> orderlist=new ArrayList<org.springframework.data.domain.Sort.Order>();
		
		for (int i = 0; null != page.getOrder() && i < page.getOrder().size(); i++) {
			
			
			Order order = page.getOrder().get(i);
			
			Columns columns = page.getColumns().get(order.getColumn());
			if("asc".equals(order.getDir())){
				orderlist.add(new org.springframework.data.domain.Sort.Order(Direction.ASC, columns.getData()));
			}else{
				orderlist.add(new org.springframework.data.domain.Sort.Order(Direction.DESC, columns.getData()));
			}
			
			
		}
		
		if(orderlist!=null&&orderlist.size()>0){
			Sort sort=new Sort(orderlist);
			return sort;
		}
		return null;
	}
	
	
	//为什么只有一个 排序呢, datatable现在就是 排序一个.
	public static <T> org.springframework.data.domain.Sort.Order getStringOrderBydataTable(Pagination<T> page) {
		
		List<org.springframework.data.domain.Sort.Order> orderlist=new ArrayList<org.springframework.data.domain.Sort.Order>();
		org.springframework.data.domain.Sort.Order sortorder=null; 
		for (int i = 0; null != page.getOrder() && i < page.getOrder().size(); i++) {
			
			
			Order order = page.getOrder().get(i);
			
			Columns columns = page.getColumns().get(order.getColumn());
			if("asc".equals(order.getDir())){
				sortorder=new org.springframework.data.domain.Sort.Order(Direction.ASC, columns.getData());
				break;
			}else{
				sortorder=new org.springframework.data.domain.Sort.Order(Direction.DESC, columns.getData());
				break;
			}
			
			
		}
		
		return sortorder;
	}
	//end   ...........................................................
	
	

}
