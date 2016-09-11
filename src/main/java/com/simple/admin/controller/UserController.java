package com.simple.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.common.util.AjaxWebUtil;
import com.simple.model.PageResult;
import com.simple.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public String list(String phone,int userStatus,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response){
		try {
			PageResult pr = userService.queryPage(phone,userStatus, pageIndex, pageSize);
			return  AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}
		catch (Exception e){
			return  AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败", null);
		}
	}
	
	@RequestMapping(value = "updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public String updateStatus(int status,String phone,HttpServletRequest request, HttpServletResponse response) {
		try {
			userService.updateStatus(phone, status);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
		}catch(Exception e) {
			log.error("注册失败",e);
			e.printStackTrace();
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败", null);
		}
	}
}
