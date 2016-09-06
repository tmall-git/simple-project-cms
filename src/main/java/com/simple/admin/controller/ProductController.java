package com.simple.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.common.util.AjaxWebUtil;
import com.simple.constant.Constant;
import com.simple.model.PageResult;
import com.simple.service.ProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	/**
	 * 产品列表
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public String productPhonelist(String name,String owner,int productStatus,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response){
		try {
			PageResult pr = productService.query(name, owner, productStatus, pageIndex, pageSize);
			return  AjaxWebUtil.sendAjaxResponse(request, response, true,"查询产品成功", pr);
		}
		catch (Exception e){
			e.printStackTrace();
			return  AjaxWebUtil.sendAjaxResponse(request, response, false,"查询产品错误:"+e.getLocalizedMessage(), null);
		}
	}
	
	/**
	 * 删除商品
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(int id,int productStatus,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response){
		try {
			productService.updateProductStatus(id, Constant.PRODUCT_STATUS_DELETE);
			return  AjaxWebUtil.sendAjaxResponse(request, response, true,"删除成功", null);
		}
		catch (Exception e){
			e.printStackTrace();
			return  AjaxWebUtil.sendAjaxResponse(request, response, false,"删除产品错误:"+e.getLocalizedMessage(), null);
		}
	}
}
