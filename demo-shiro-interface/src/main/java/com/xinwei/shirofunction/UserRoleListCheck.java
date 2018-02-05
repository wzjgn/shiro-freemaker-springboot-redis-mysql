package com.xinwei.shirofunction;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 给用户添加role时使用。
 * @author ouburikou
 *
 */
@Slf4j
@Data
public class UserRoleListCheck {

	private String username;
	private String roleName;
	private boolean check=false;
	
	
}
