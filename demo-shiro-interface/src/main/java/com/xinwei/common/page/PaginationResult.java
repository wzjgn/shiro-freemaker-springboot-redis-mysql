package com.xinwei.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <b>类名</b>：PaginationResult.java<br>
 * <p><b>标题</b>：中信国安  </p>
 * <p><b>描述</b>：
 *         DataTables 返回分页数据的 对象
 *         实现Serializable 接口.
 * </p>
 * <p><b>版权声明</b>：Copyright (c) 2016</p>
 * <p><b>公司</b>：中信国安 </p>
 * @author <font color='blue'>zhangzq</font> 
 * @version 1.0
 * @date  2016年7月7日 下午2:09:03
 * @param <T>
 */
public class PaginationResult<T> implements Serializable{

	

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	public PaginationResult() {
		
	}

	/**
	 * 暂时没有发现这个字段的含义.
	 * datatables 提示这是必须的字段,暂时保留.
	 */
	private int draw;
	
	/**
	 * 经过查询条件查询的总个数.
	 * (一般情况下 默认和recordsTotal 保持一样就可以)
	 */
	private long recordsFiltered;
	
	/**
	 * 查询的总个数
	 */
	private long recordsTotal;
	
	/**
	 * 查询某页返回的数据.
	 */
	private List<T> data;

	
	public int getDraw() {
		return draw;
	}

	
	public void setDraw(int draw) {
		this.draw = draw;
	}

	
	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public List<T> getData() {
		return data;
	}

	
	public void setData(List<T> data) {
		this.data = data;
	}

	

	

	


	
	

	

}
