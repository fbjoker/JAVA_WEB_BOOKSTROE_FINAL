package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	/**
	 * 订单编号 常用是的时间戳+密文+userID
	 */
			
	private String orderId;
	/**
	 * 订单商品总数量
	 */
	private Integer totalCount;
	/**
	 * 订单总金额
	 */
	private Double totalAmount;
	/**
	 * 订单状态
	 */
	private int status;
	/**
	 * 订单创建时间
	 */
	private Date createTime;
	/**
	 * 订单对应的用户
	 */
	private Integer userId;
	public Order() {
		super();
	}
	public Order(String orderId, Integer totalCount, Double totalAmount, int status, Date createTime, Integer userId) {
		super();
		this.orderId = orderId;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
		this.status = status;
		this.createTime = createTime;
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount + ", status="
				+ status + ", createTime=" + createTime + ", userId=" + userId + "]";
	}
	
}
