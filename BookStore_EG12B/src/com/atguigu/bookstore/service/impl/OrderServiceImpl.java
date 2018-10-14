package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao od=new OrderDaoImpl();
	private OrderItemDao oid=new OrderItemDaoImpl();
	private BookService bs= new BookServiceImpl();

	@Override
	public String  saveOrder(Cart cart, User user) {
	
		Date data=new Date();
		
		
		String orderId=""+System.currentTimeMillis()+user.getId();
		
		int status=0;
		//保存订单到数据库
		Order order=new Order(orderId,cart.getTotalCount(),cart.getTotalAmount(),status,data,user.getId());
		od.saveOrder(order);
		
		//将购物项转换为订单项存到数据库
		List<CartItem> list = cart.getMap2list();
		
		for (CartItem cartItem : list) {
			Book book = cartItem.getBook();
			OrderItem orderItem = new OrderItem(null,book.getTitle(),book.getImgPath(),book.getAuthor(),book.getPrice(),cartItem.getCount(),cartItem.getAmount(),orderId );
			
					
			int saveOrderItem = oid.saveOrderItem(orderItem);
			
			
			book.setStock(book.getStock()-cartItem.getCount());
			book.setSales(book.getSales()+cartItem.getCount());
			bs.updateBook(book);
		}
		// 清除session里面的购物信息
		cart.deleteAll();
		
		return orderId;
	}

	@Override
	public List<Order> getAllOrder() {
		return od.getAllOrder();
	}

	@Override
	public List<Order> getOrderByUser(String userId) {
		return od.getOrderByUser(userId);
	}

	@Override
	public int updateStatusByid(String orderId, int status) {
		return od.updateStatusByid(orderId, status);
	}

	@Override
	public int saveOrderItem(OrderItem orderItem) {
		return oid.saveOrderItem(orderItem);
	}

	@Override
	public List<OrderItem> getOrderItemsByuserId(String orderId) {
		return oid.getOrderItemsByuserId(orderId);
	}

}
