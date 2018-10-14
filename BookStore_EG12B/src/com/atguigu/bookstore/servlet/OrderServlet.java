package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

public class OrderServlet extends BaseServlet {
	
	
	private static final long serialVersionUID = 1L;
	
	
	OrderService os=new OrderServiceImpl();
	
	protected void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		
		
		
		String orderId = os.saveOrder(cart, user);

		System.out.println(orderId);

		// 结账操作比较重要就重定向,避免重复提交
		session.setAttribute("orderId", orderId);

		response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
		
		
	}
	
	
	protected void getAllOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> list = os.getAllOrder();
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		response.sendRedirect(request.getContextPath()+"/pages/manager/order_manager.jsp");
	}
	
	protected void getOrderByUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		

			List<Order> list = os.getOrderByUser(user.getId() + "");
			session.setAttribute("list", list);
			//用send方法跳过去会直接显示没有订单
			response.sendRedirect(request.getContextPath() + "/OrderManngerServlet?type=getAllOrder");
			//跳转到/pages/order/order.jsp显示订单集合数据
			//request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
		
	}
	
	
	
	protected void sendGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		 int status=1;
		 os.updateStatusByid(orderId, status);
		 System.out.println("ok");
		response.sendRedirect(request.getHeader("referer"));
	
	}
	
	protected void takeGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		 int status=2;
		os.updateStatusByid(orderId, status);
		System.out.println("ok");
		System.out.println(orderId);
		response.sendRedirect(request.getHeader("referer"));
	
	}

}
