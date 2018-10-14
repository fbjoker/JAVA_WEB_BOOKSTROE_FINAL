package com.atguigu.bookstore.bean;

import java.io.Serializable;

public class OrderItem implements Serializable {
	/**
	 * 订单项ID
	 */
	private Integer orderItemId;
	/**
	 * 订单项商品名称
	 */
	private String title;
	/**
	 * 订单项作者
	 */
	private String author;
	/**
	 * 订单项图片路径
	 */

	private String imgPath;
	/**
	 * 订单项价格
	 */
	private Double price;
	/**
	 * 订单项数量
	 */
	private Integer count;
	/**
	 * 订单项总金额
	 */
	private Double amount;
	/**
	 * 订单项属于哪个订单号
	 */
	private String orderId;

	



	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}



	public OrderItem(Integer orderItemId, String title, String author, String imgPath, Double price, Integer count,
			Double amount, String orderId) {
		super();
		this.orderItemId = orderItemId;
		this.title = title;
		this.author = author;
		this.imgPath = imgPath;
		this.price = price;
		this.count = count;
		this.amount = amount;
		this.orderId = orderId;
	}

	

	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", title=" + title + ", author=" + author + ", imgPath="
				+ imgPath + ", count=" + count + ", amount=" + amount + ", orderId=" + orderId + "]";
	}

}
