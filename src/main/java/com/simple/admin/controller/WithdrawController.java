package com.simple.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruanwei.tool.SmsClient;
import com.simple.common.util.AjaxWebUtil;
import com.simple.constant.Constant;
import com.simple.model.Account;
import com.simple.model.PageResult;
import com.simple.service.BaseService;
import com.simple.service.WithdrawService;

@Controller
@RequestMapping("account")
public class WithdrawController {

	@Autowired
	private WithdrawService withdrawService;
	
	@Autowired
	private BaseService baseService;
	
	@RequestMapping(value = "finishcash",method=RequestMethod.POST)
	@ResponseBody
	public String finishcash(String id,HttpServletRequest request, HttpServletResponse response){
		try {
			Account a = withdrawService.queryById(id);
			if (null == a) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false, "记录不存在", null);
			}
			if (a.getStatus()!=Constant.CASH_STATUS_COMMIT) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false, "该状态不允许提现", null);
			}
			withdrawService.updateAccountFinised(a);
			SmsClient.sendMsg(a.getApplyPhone(), "提现金额:"+a.getCashAmount()+"成功.");
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "提现成功", null);
		}catch(Exception e) {
			e.printStackTrace();
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "提现失败："+e.getLocalizedMessage(), null);
		}
	}
	
	@RequestMapping(value = "info",method=RequestMethod.GET)
	@ResponseBody
	public String info(String id,HttpServletRequest request, HttpServletResponse response){
		try {
			Account a = withdrawService.queryById(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询成功", a);
		}catch(Exception e) {
			e.printStackTrace();
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询失败："+e.getLocalizedMessage(), null);
		}
	}
	
	@RequestMapping(value = "allAccountList",method=RequestMethod.GET)
	@ResponseBody
	public String accountList(int pageIndex,int pageSize,int status,String phone,HttpServletRequest request, HttpServletResponse response){
		try {
			PageResult a = withdrawService.queryAll(pageIndex, pageSize, phone,status);
			return AjaxWebUtil.sendAjaxResponse(request, response, true, "查询提现成功", a);
		}catch(Exception e) {
			e.printStackTrace();
			return AjaxWebUtil.sendAjaxResponse(request, response, false, "查询提现失败："+e.getLocalizedMessage(), null);
		}
	}
}
