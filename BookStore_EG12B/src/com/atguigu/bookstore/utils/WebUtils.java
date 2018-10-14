package com.atguigu.bookstore.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;

public class WebUtils {
	
	
	public  static <T> T  parm2Bean(T t,HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}

	public static String getPath(HttpServletRequest request) {
		//当用户访问分页数据时，可以获取到 分页对应的前段地址http://localhost:8080/BookStore_EG04/BookManagerServlet?type=findPage&pageNumber=1
    	//获取 8080端口号后面到?之间的内容
    	String uri = request.getRequestURI();
    	//获取get方式提交的请求参数列表
    	String queryString = request.getQueryString();
    	//截取请求参数type=findPage
    	if(queryString!=null && queryString.contains("&pageNumber")) {
    		queryString  = queryString.substring(0, queryString.indexOf("&pageNumber"));
    	}
    	//System.out.println(uri+"?"+queryString);
    	String path = uri+"?"+queryString;//拼接页面分页导航栏需要的 前段地址
    	return path;
	}
}
