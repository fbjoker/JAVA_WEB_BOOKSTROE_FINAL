package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Book book;
	private int count;
	private double amount;

	public CartItem() {
		super();
	}

	public CartItem(Book book, int count, double amount) {
		super();
		this.book = book;
		this.count = count;
		this.amount = amount;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getAmount() {
		BigDecimal bd1 = new BigDecimal(""+getBook().getPrice());
		BigDecimal bd2 = new BigDecimal(""+getCount());
		BigDecimal mul = bd1.multiply(bd2);
		
		return mul.doubleValue();
	}

	/*public void setAmount(double amount) {
		this.amount = amount;
	}*/

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + amount + "]";
	}

}
