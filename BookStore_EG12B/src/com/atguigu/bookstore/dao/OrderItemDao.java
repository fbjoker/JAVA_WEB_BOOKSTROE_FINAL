package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {
	
	 int saveOrderItem(OrderItem orderItem);
	 
	 
	 /**
	  * 查询当前订单所有的订单项集合
	  * @param orderId
	  * @return
	  */
	 List<OrderItem> getOrderItemsByuserId(String orderId); 

}
