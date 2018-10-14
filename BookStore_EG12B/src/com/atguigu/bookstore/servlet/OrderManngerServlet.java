package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderManngerServlet
 */
public class OrderManngerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	OrderService os=new OrderServiceImpl();

	protected void getAllOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> list = os.getAllOrder();
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		System.out.println("ok,已发货,快递小哥正在派送中");
		//response.sendRedirect(request.getContextPath()+"/pages/manager/order_manager.jsp");
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}
	
	protected void sendGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		 int status=1;
		 os.updateStatusByid(orderId, status);
		 System.out.println("ok");
		response.sendRedirect(request.getHeader("referer"));
	
	}

}
