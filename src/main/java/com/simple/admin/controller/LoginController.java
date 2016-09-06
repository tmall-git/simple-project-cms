package com.simple.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.admin.util.LoginUserUtil;
import com.simple.common.config.EnvPropertiesConfiger;
import com.simple.common.util.AjaxWebUtil;
import com.simple.constant.Constant;
import com.simple.model.User;
import com.simple.service.UserService;
import com.simple.weixin.auth.OAuthAccessToken;
import com.simple.weixin.auth.OAuthUserInfo;
import com.simple.weixin.auth.WeiXinAuth;

@Controller
@RequestMapping("")
public class LoginController {

	private static final String ACCOUNT = EnvPropertiesConfiger.getValue("account");
	private static final String PASSWORD = EnvPropertiesConfiger.getValue("password");
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 跳转到微信授权，然后回跳到微信支付页面
	 * @param code
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "login",method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
	
	@RequestMapping(value = "doLogin",method=RequestMethod.POST)
	public String doLogin(String userName,String password,HttpServletRequest request, HttpServletResponse response){
		try {
			if (!ACCOUNT.equals(userName)) {
				AjaxWebUtil.sendAjaxResponse(request, response, "管理员帐号不存在.");
				return null;
			}
			
			if (!PASSWORD.equals(password)) {
				AjaxWebUtil.sendAjaxResponse(request, response, "密码错误.");
				return null;
			}
			
			User user = new User();
			user.setUserName("管理员");
			LoginUserUtil.setCurrentUser(request, user);
			return "main";
		}catch(Exception e) {
			AjaxWebUtil.sendAjaxResponse(request, response, "登录失败:"+e.getLocalizedMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "logout",method=RequestMethod.GET)
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response){
		LoginUserUtil.removeCurrentUser(request);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"登出成功", null);
	}
}
