package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
	
		if(user==null) {
			//通过request域共享数据，设置一个错误消息
			request.setAttribute("errorMsg", "用户未登录！");
			//查询失败，登录失败 ， 转发到登录页面让用户继续登录
			//转发由服务器解析，绝对路径基准地址到项目名
			//转发特点： 一次请求，地址栏地址不变，打开的页面和url地址不对应 ， 转发会造成转发后的页面中的相对路径失效
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			//解决相对路径失效的问题
		}else {
			chain.doFilter(request, response);
		}

	}

}
