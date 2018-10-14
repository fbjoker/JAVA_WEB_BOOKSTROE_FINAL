package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//同一个用户登录时都是分配的同一个线程
		Thread thread = Thread.currentThread();
		//保证当前线程连接的数据库连接是同一个
		Connection conn = JDBCUtils.getConn();
		
		try {
			conn.setAutoCommit(false);
			chain.doFilter(request, response);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//有异常时，可以根据具体情况给用户响应错误页面
			response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
		}finally {
			
				JDBCUtils.closeCon();
			
		}
		
		
		
		
		
	}


}
