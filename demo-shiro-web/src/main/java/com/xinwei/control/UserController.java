package com.xinwei.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xinwei.common.page.Pagination;
import com.xinwei.common.page.PaginationResult;
import com.xinwei.service.ShiroRoleService;
import com.xinwei.service.ShiroUserService;
import com.xinwei.shiro.MyShiroRealm;
import com.xinwei.shirofunction.ShiroRole;
import com.xinwei.shirofunction.ShiroUser;
import com.xinwei.shirofunction.UserRoleListCheck;
import com.xinwei.spring.boot.autoconfigure.shiro.ShiroProperties;
import com.xinwei.utils.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro测试Controller
 *
 * @create 2016年1月13日
 */
@Controller
@Slf4j
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	 
	@Autowired
	MyShiroRealm myShiroRealm;
	
	/*@Autowired
	UserService userService;*/

	@Autowired
	ShiroUserService shiroUserService;
	 
	@Autowired
	ShiroRoleService shiroRoleService;

	@Autowired
	private ShiroProperties shiroProperties;
	
 
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		logger.info("--info--");
		log.debug("--debug--");
		model.addAttribute("user", new ShiroUser());
		return "login";
	}

	@RequestMapping(value = "/index")
	public String index() {

		return "login";
	}
	@RequestMapping(value = "/")
	public String login() {

		return "login";
	}
	@RequestMapping(value = "/regist")
	public String regist() {
		
		return "regist";
	}

	@RequestMapping(value = "/forgetPwd")
	public String forgetPwd() {
		return "forgetPwd";
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/user/password")
	@ResponseBody
	public String password(String password, String oldpassword, String username) {

		ResponseJson responseJson = new ResponseJson();
		ShiroUser user = new ShiroUser();
		try {
			user = shiroUserService.findByUsername(username);

			 

			
			oldpassword = new Md5Hash(oldpassword, "www",1024).toBase64();
			
			
			if (!oldpassword.equals(user.getPassword())) {
				responseJson.setMsg("passwordError");
			} else {

				user.setPassword(new Md5Hash(password, "www", 1024).toBase64());
				 
				shiroUserService.save(user);
				responseJson.setMsg("success");
			}

		} catch (Exception e) {
			responseJson.setMsg("exception");

			logger.info(e.toString());
		}

		logger.info(JSONObject.toJSONString(responseJson));
		return JSONObject.toJSONString(responseJson);
	}

	 
	
	/**
	 * 校验用户名或者邮箱是否存在
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/checkNameOrEmallExist")
	@ResponseBody
	public String checkNameOrEmallExist(ShiroUser shiroUser) {
		Map<String, String> map=new HashMap<String,String>();
		ResponseJson responseJson = new ResponseJson();
		try {
			responseJson = shiroUserService.checkNameOrEmallExist(shiroUser);
			if("true".equals(responseJson.getMsg())){
				
				map.put("valid", "true");
				return JSONObject.toJSONString(map);
			}else{
				map.put("valid", "false");
				return JSONObject.toJSONString(map);
			}
			
		} catch (Exception e) {
			map.put("valid", "true");
			logger.info(e.toString());
		}
		
		logger.info(JSONObject.toJSONString(responseJson));
		return JSONObject.toJSONString(map);
	}
	


	/**
	 * 管理员在后台修改 用户 用户注册,企业用户注册，管理员添加本单位用户、上级用户。
	 * 用户类别：0-企业用户,1-本单位普通用户，2-系统管理员，3-上级单位
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/editUser")
	@ResponseBody
	public String editUser(ShiroUser shiroUser) {

		ResponseJson responseJson = new ResponseJson();

		try {
			responseJson = shiroUserService.editUser(shiroUser);
			
			//begin 添加记录日志功能
			Subject currentUser = SecurityUtils.getSubject();
			String logOperatorName = (String) currentUser.getPrincipal();
			//end   添加记录日志功能
			
		} catch (Exception e) {
			responseJson.setMsg("exception");
			logger.info(e.toString());
		}

		return JSONObject.toJSONString(responseJson);
	}

	/**
	 * 管理员在后台添加 用户 用户注册,企业用户注册，管理员添加本单位用户、上级用户。
	 * 用户类别：0-企业用户,1-本单位普通用户，2-系统管理员，3-上级单位
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/addUser")
	@ResponseBody
	public String addUser(ShiroUser shiroUser) {
		ResponseJson responseJson = new ResponseJson();
		try {
			responseJson = shiroUserService.addUser(shiroUser);
			
			//begin 添加记录日志功能
			Subject currentUser = SecurityUtils.getSubject();
			String logOperatorName = (String) currentUser.getPrincipal();
			if("success".equals(responseJson.getMsg())){
			}
			
			//end   添加记录日志功能
			
		} catch (Exception e) {
			responseJson.setMsg("exception");
			logger.info(e.toString());
		}
		return JSONObject.toJSONString(responseJson);
	}
	
	/**
	 * 获取role列表，以及当前用户role。
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/toAddRolePage")
	@ResponseBody
	public String toAddRolePage(String username) {
		ResponseJson responseJson = new ResponseJson();
		

		ShiroUser shiroUser = shiroUserService.findByUsername(username);
		List<ShiroRole> roleListC = shiroUser.getRoleList();
		List<ShiroRole> roleListA = shiroRoleService.findRoleList();
		 
		Map<Integer,Boolean> map =new HashMap<Integer,Boolean>();
		
		for(int i=0;i<roleListC.size();i++){
			map.put(roleListC.get(i).getId(), true);
			 
		}
		
		
		
		List<UserRoleListCheck> list =new ArrayList<>();
		
		for(int i=0;i<roleListA.size();i++){
			 
			UserRoleListCheck userRoleListCheck = new UserRoleListCheck();
			userRoleListCheck.setUsername(shiroUser.getUsername());
			userRoleListCheck.setRoleName(roleListA.get(i).getRoleName());
			
			if(map.get(roleListA.get(i).getId())!=null){
				userRoleListCheck.setCheck(true);
			}
			list.add(userRoleListCheck);
			
		}
		
		logger.info(JSONObject.toJSONString(list));

		return JSONObject.toJSONString(list);
	}
	
	
	
	/**
	 * 用户角色修改
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/editUserRole")
	@ResponseBody
	public String editUserRole(String username,String role) {

		logger.info(role);
		ResponseJson responseJson = new ResponseJson();

		try {
			responseJson = shiroUserService.editUserRole(username, role);
			
			//begin 添加记录日志功能
			Subject currentUser = SecurityUtils.getSubject();
			String logOperatorName = (String) currentUser.getPrincipal();
			if("success".equals(responseJson.getMsg())){
			}
			
			//end   添加记录日志功能
			
		} catch (Exception e) {
			responseJson.setMsg("exception");
			responseJson.setSuccess(true);
			logger.info(e.toString());
		}

		return JSONObject.toJSONString(responseJson);
	}
	
	
	 /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "/admin/user/del/{ids}")
    @ResponseBody
    String Del(@PathVariable("ids") String ids) {
        ResponseJson responseJson = new ResponseJson();
        
       
        try {
        	logger.info("ids====="+ids);
            responseJson = shiroUserService.del(ids);
            
            //begin 添加记录日志功能
			Subject currentUser = SecurityUtils.getSubject();
			String logOperatorName = (String) currentUser.getPrincipal();
			//end   添加记录日志功能
            
        } catch (Exception e) {
        	logger.info(e.toString());
            responseJson.setMsg(e.getMessage());
        
       }
        return JSONObject.toJSONString(responseJson);
    }

    


	/**
	 * 验证用户登录名是否存在,如果不存在 返回success。
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/usernameExistValid")
	@ResponseBody
	public String usernameExistValid(String username) {

		ResponseJson responseJson = new ResponseJson();

		ShiroUser shiroUserTemp = new ShiroUser();
		shiroUserTemp = shiroUserService.findByUsername(username);
		if (shiroUserTemp == null) {
			responseJson.setMsg("success");
		} else {
			responseJson.setMsg("false");
		}
		return JSONObject.toJSONString(responseJson);

	}

	  

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public String resetPassword(ShiroUser shiroUser) {

		ResponseJson responseJson = new ResponseJson();

		try {
			logger.info("shiroUser.getUsername()=" + shiroUser.getUsername());
			logger.info("shiroUser.getPassword()=" + shiroUser.getPassword());
			String password = new Md5Hash(shiroUser.getPassword(), "www", 1024).toBase64();
			shiroUser = shiroUserService.findByUsername(shiroUser.getUsername());

			shiroUser.setPassword(password);
			shiroUserService.save(shiroUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseJson.setMsg("success");

		return JSONObject.toJSONString(responseJson);

	}

	/**
	 * 审核
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/audit")
	@ResponseBody
	public String audit(String auditStatus, String username) {

		ResponseJson responseJson = new ResponseJson();
		try {
			
				responseJson = shiroUserService.audit(username, auditStatus);
				responseJson.setMsg("success");
				//begin 添加记录日志功能
				Subject currentUser = SecurityUtils.getSubject();
				String logOperatorName = (String) currentUser.getPrincipal();
				//end   添加记录日志功能

		} catch (Exception e) {
		 
			responseJson.setMsg(e.getMessage());
		}

		
		return JSONObject.toJSONString(responseJson);
	}
	
	
	

	/**
	 * 进入用户列表页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/table_managed")
	public String table_managed(Model model) {

		return "admin/table_managed";
	}

	/**
	 *
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/findUserList")
	@ResponseBody
	String findListByUserName(@RequestBody Pagination<ShiroUser> pagination) {

		PaginationResult<ShiroUser> pageResonse = shiroUserService.findAllByPage(pagination);

		JSONObject jsonObject = (JSONObject) JSON.toJSON(pageResonse);
		logger.info(jsonObject.toJSONString());
		return jsonObject.toJSONString(pageResonse, SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteMapNullValue);
	}

	/**
	 *
	 * 获取待修改用户的值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/toEditUserPage")
	@ResponseBody
	String toEditUserPage(String username, HttpServletRequest request) {

		ShiroUser shiroUser = shiroUserService.findByUsername(username);
		logger.info(JSONObject.toJSONString(shiroUser));

		return JSONObject.toJSONString(shiroUser);

	}

	 

	/**
	 *
	 * 进入强制修改密码，完善用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/user/toInformationPage")
	public String toInformationPage(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();

		String username = (String) currentUser.getPrincipal();
		ShiroUser shiroUser = shiroUserService.findByUsername(username);
		request.setAttribute("shiroUser", shiroUser);

		return "/admin/user/information";

	}
	
	
	 
	@RequestMapping(value = "/md5")
	@ResponseBody
	public String md5w() {
		String md5 = new Md5Hash("111111", "www",1024).toBase64();
        return md5; 
	}
	
	 
	
	 

	 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@Valid ShiroUser user, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		// if(bindingResult.hasErrors()){
		// return "login";
		// }

		ResponseJson responseJson = new ResponseJson();
		String username = user.getUsername();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		// token.setRememberMe(true);
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		

		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			 
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
			
			//begin 添加记录日志功能
			String logOperatorName = (String) currentUser.getPrincipal();
			//end   添加记录日志功能
			
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addFlashAttribute("message", "账户不存在");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		} catch (DisabledAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户未审核");
			redirectAttributes.addFlashAttribute("message", "账户未审核");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");

			SavedRequest re = WebUtils.getAndClearSavedRequest(request);

			logger.info("redirect:" + ((re == null || re.getRequestUrl() == null) ? shiroProperties.getSuccessUrl()
					: re.getRequestUrl()));
			String url = ((re == null || re.getRequestUrl() == null) ? shiroProperties.getSuccessUrl()
					: re.getRequestUrl());

			ShiroUser shiroUser = shiroUserService.findByUsername(username);
			 
				responseJson.setMsg("success");
				responseJson.setUrl(url);
				return JSONObject.toJSONString(responseJson);
			 

		} else {
			token.clear();
			Map<String, String> map = (Map) redirectAttributes.getFlashAttributes();
			String reason = map.get("message");

			responseJson.setMsg("false");
			responseJson.setReturnStr(reason);
			return JSONObject.toJSONString(responseJson);// "{\"success\":true,\"msg\":false,\"returnStr\":\""+reason+"\"}"
															// ;
		}
	}

	@RequestMapping(value = "/admin/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		// redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/login";
	}

	@RequestMapping(value = "/resetPasswordError")
	public String resetPasswordError(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		// redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "/resetPasswordError";

	}



}