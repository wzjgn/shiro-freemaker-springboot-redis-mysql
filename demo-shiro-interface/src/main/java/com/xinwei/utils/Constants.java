package com.xinwei.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

	/**
	 * 企业用户需要审核才能登陆，非企业用户默认审核成功
	 */
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
	
	
	/*****************************************报表导出 start*********************************************************/
	 
	public static List<String>  list1 =new ArrayList<String>();
	public static List<String>  list2 =new ArrayList<String>();
	public static Map<String, List> map=new HashMap<String, List>();
	public static Map<String, String> mapValue=new HashMap<String, String>();
	
	static{
		
		list1.add("与工业控制系统网络相连的网络");
		list1.add("连接方式");
		list1.add("连接时间");
		list1.add("连接后身份认证方式");
		list1.add("连接管理制度");
		list1.add("合法系统间互联的识别和认证技术措施");
		
		
		list2.add("移动存储介质使用管理制度");
		list2.add("系统网络和公共网之间交叉使用移动存储介质");
		list2.add("工业控制系统主机的存储介质使用情况检查");
		list2.add("移动存储介质自动播放功能");
		list2.add("工业控制系统与其他系统之间专用的安全信息交换途径");
		list2.add("移动存储介质销毁处置流程");
		
		 
		
		
		map.put("工业控制系统网络与公共网络的连接管理",list1);
		map.put("存储介质使用管理",list2);
		 
		
		
		mapValue.put("与工业控制系统网络相连的网络", "内部局域网-互联网-企业管理网-其他网络-无连接");
		mapValue.put("连接方式","直接相连-采取隔离措施后连接-防火墙");
		mapValue.put("连接时间","始终连接-有需要时连接-无连接");
		mapValue.put("连接后身份认证方式","口令-数字认证技术-无认证");
		mapValue.put("连接管理制度","审批备案-定期检查-风险评估-隔离措施有效性验证-无");
		mapValue.put("合法系统间互联的识别和认证技术措施","有-无");
		
		mapValue.put("移动存储介质使用管理制度","有-无");
		mapValue.put("系统网络和公共网之间交叉使用移动存储介质","禁止-未禁止-未要求");
		mapValue.put("工业控制系统主机的存储介质使用情况检查","定期-不定期-不检查");
		mapValue.put("移动存储介质自动播放功能","明文禁止-未禁止-未要求");
		mapValue.put("工业控制系统与其他系统之间专用的安全信息交换途径","有-无");
		mapValue.put("移动存储介质销毁处置流程","有-无");
		
		
		
		
	}
	
     
	
	
	/*****************************************报表导出 end*********************************************************/
	
	
	
	
	
	
}
