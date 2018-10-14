package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理用户相关的请求
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService service = new UserServiceImpl();
	
	
	
	protected void userCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		User loginCheck = service.loginCheck(username);
		System.out.println(loginCheck);
		if(loginCheck!=null) {
			//不可以使用
			response.getWriter().write("0");
		}else {
			//可以使用
			response.getWriter().write("1");
		}
	}
	
	
	//处理登录请求的方法
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//将参数封装到user对象中
		User loginUser = new User(null, username, password, null);
		//2、调用service处理请求
		//登录查询到的user对象，包括账号密码邮箱以及用户id信息的用户对象
		//User user = dao.getUserByUsernameAndPassword(loginUser);
		User user = service.login(loginUser);
		//3、根据处理结果给用户响应
		if(user==null) {
			//通过request域共享数据，设置一个错误消息
			request.setAttribute("errorMsg", "账号或密码错误！");
			//查询失败，登录失败 ， 转发到登录页面让用户继续登录
			//转发由服务器解析，绝对路径基准地址到项目名
			//转发特点： 一次请求，地址栏地址不变，打开的页面和url地址不对应 ， 转发会造成转发后的页面中的相对路径失效
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			//解决相对路径失效的问题
		}else {
			//登录成功 ， 重定向到登录成功页面
			//重定向特点： 两次请求两个响应 ,重定向地址是一个特殊响应交给浏览器解析使用，绝对路径基准地址到服务器
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}
	
	
	
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	//处理注册请求的方法
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		 HttpSession session = request.getSession();
		 String  key = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
		 
		 if(code!=null&&code.equals(key)) {
			//1、获取注册请求参数
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String email = request.getParameter("email");
				User user = new User(null, username, password, email);
				
				
				//2、调用service处理请求
				//int i = dao.saveUser(user);
				boolean b = service.regist(user);
				//3、根据处理结果给用户响应
				if(b) {
					//注册成功， 重定向到成功页面
					response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
				}else {
					//向域中设置错误消息
					request.setAttribute("errorMsg", "账号已存在！！");
					//注册失败 ， 转发到注册页面让用户继续注册
					request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
				}
				//修改注册页面表单提交的action地址为RegistServlet
		 }else {
			//向域中设置错误消息
				request.setAttribute("errorMsg", "验证码错误！！");
				//注册失败 ， 转发到注册页面让用户继续注册
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			
		 }
		 
		 //注销本次验证码,防止重复注册
		 session.removeAttribute("KAPTCHA_SESSION_KEY");
		
	}
}
