package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目中所有Servlet的基类，可以控制子类方法调用
 * 子类不能实现doGet和doPost方法，保证所有的访问Servlet的请求都会进入到BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		/**
		 * 如果希望Servlet能够处理多个请求，通过反射能够调用type值对应的方法，必须满足以下要求：
		 * 	1. 所有的请求访问XXXServlet时必须提交type参数，告诉servlet要调用的方法
		 * 	2. type属性值必须和Servlet中的对应的方法名一样
		 * 	3. Servlet中处理请求的方法结构必须和doGet方法一样
		 * 
		 * 
		 * 1、this代表UserServlet的对象
		 * 2、this.getClass 获取的是UserServlet.class
		 * 3、type参数值正好对应 一个方法名
		 * 4、所有的处理请求的方法形参列表都是一样的
		 * 反射： 在一个类中可以根据方法名和形参类型列表确定唯一的一个方法对象
		 * 		通过方法对象，传入执行方法的对象和实参列表可以调用方法
		 * 
		 */
		//通过反射自动调用type参数值对应的方法
		//1、获取调用doGet方法的类的类型
		Class clazz = this.getClass();
		//2、获取方法名[type属性值]
		String type = request.getParameter("type");
	//	System.out.println(clazz+" , "+ type);
		//3、通过形参类型列表和方法名可以在类中确定唯一的一个方法
		try {
			//参数1： 方法名 ， 参数2~n： 形参类型列表
			Method method = clazz.getDeclaredMethod(type, HttpServletRequest.class , HttpServletResponse.class);
			//4、执行对应的方法处理请求
			//参数1：调用方法的对象 ， 参数2~n：方法执行时需要的实参列表
			method.invoke(this, request , response);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		//判断用户的需求
		//1、获取type参数
		/*String type = request.getParameter("type");
		//2、判断type
		if("login".equals(type)) {
			//2.1  登录
			login(request, response);
		}else if("regist".equals(type)) {
			//2.2 注册
			regist(request, response);
		}*/
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
