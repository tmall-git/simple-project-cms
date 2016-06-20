package com.simple.admin.util;

import javax.servlet.http.HttpServletRequest;

import com.simple.constant.Constant;
import com.simple.model.SysUser;


public class LoginUserUtil {

	public static SysUser getCurrentUser(HttpServletRequest request) {
		//TODO return (SysUser) request.getSession().getAttribute(Constant.CURRENT_USER);
		SysUser su  = new SysUser();
		su.setId(1);
		su.setLeaseholderId("t10001");
		return su;
	}
	public static void setCurrentUser(HttpServletRequest request,SysUser user) {
		request.getSession().setAttribute(Constant.CURRENT_USER,user);
	}
	public static void removeCurrentUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.CURRENT_USER);
	}
	public static String getLeaseholderId(HttpServletRequest request) {
		SysUser user = getCurrentUser(request);
		//TODO 通过user获取租户ID
		return "t10001";
	}
}
