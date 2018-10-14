package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem orderItem) {
		String sql="insert into bs_orderitem (title,author,img_path,price,count,amount,order_id) "
				+ "values(?,?,?,?,?,?,?) ";
		
		return update(sql, orderItem.getTitle(),orderItem.getAuthor(),orderItem.getImgPath(),
				orderItem.getPrice(),orderItem.getCount(),orderItem.getAmount(),orderItem.getOrderId());
	}

	@Override
	public List<OrderItem> getOrderItemsByuserId(String orderId) {
		String sql="select id orderItemId,title,author,imp_path imgPath, "
				+ "price,count,amount,order_id orderId from bs_orderitem where order_id=?";
		
		return getBeanList(OrderItem.class, sql, orderId);
	}

}
