package com.xinwei.utils;

public class Constants {

	 
	public static String  userStatus_0="0";//注册用户未审核 
	public static String userStatus_1="1";//注册用户已审核
	public static String userStatus_2="2";//注册用户被锁定
	
	 
	
	public static String initPassword="111111";// 管理员在后台添加用户初始密码 111111 
	 
	
	
	/*****************************************shiro redis 管理设置 start*********************************************************/
	/**
	 * redis cache 前缀
	 */
	public final static String REDIS_SHIRO_CACHE = "shiro-cache:";

	/**
	 * redis session 前缀
	 */
	public final static String REDIS_SHIRO_SESSION = "shiro-session:";
	/*****************************************shiro redis 管理设置 end*********************************************************/
	 
	
	
	
	
}
