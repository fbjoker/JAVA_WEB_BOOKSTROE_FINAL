package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;

public interface OrderDao {
	
	int saveOrder(Order order);
	/**
	 * 管理员查询所有的订单
	 * @return
	 */
	List<Order> getAllOrder();
	
	/**
	 * 用户使用的查询订单
	 * @param userId
	 * @return
	 */
	List<Order> getOrderByUser(String  userId);
	
	/**
	 * 修改状态值,
	 * @param orderId
	 * @param status
	 * @return
	 */
	int updateStatusByid(String orderId,int status);
}
