package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public int saveOrder(Order order) {
		String sql="insert into bs_order (id,total_count,total_amount,state,create_time,user_id) "
				+ "values(?,?,?,?,?,?) ";
		return update(sql, order.getOrderId(),order.getTotalCount(),order.getTotalAmount(),
				order.getStatus(),order.getCreateTime(),order.getUserId());
	}

	@Override
	public List<Order> getAllOrder() {
		String sql="select  id orderId,total_count totalCount,total_amount totalAmount "
				+ ",state status,create_time createTime,user_id userId from bs_order" ;
		return getBeanList(Order.class, sql);
	}

	@Override
	public List<Order> getOrderByUser(String  user_id) {
		String sql="select  id orderId,total_count totalCount,total_amount totalAmount "
				+ ",state status,create_time createTime,user_id userId from bs_order where user_id=?" ;
		return getBeanList(Order.class, sql,user_id);
	}

	@Override
	public int updateStatusByid(String orderId, int status) {
		String sql="update bs_order set state=? where id=?";
		
		return update(sql, status,orderId);
	}

}
