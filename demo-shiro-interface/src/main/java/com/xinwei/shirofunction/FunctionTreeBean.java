package com.xinwei.shirofunction;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 展示功能Tree时使用。
 * @author ouburikou
 *
 */
@Slf4j
@Data
public class FunctionTreeBean {

	private Integer id;
	private Integer pId;
	private String name;
	private boolean checked=false;
	private boolean open=true;
	
	
}
