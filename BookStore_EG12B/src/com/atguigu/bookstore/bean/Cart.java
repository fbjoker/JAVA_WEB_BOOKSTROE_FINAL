package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	Map<String, CartItem> map=new LinkedHashMap<String, CartItem>();
	int totalCount;
	double totalAmount;

	public Cart() {
		super();
	}

/*	public Cart(Map<String, CartItem> map, int totalCount, double totalAmount) {
		super();
		this.map = map;
		this.totalCount = totalCount;
		this.totalAmount = totalAmount;
	}*/

	public Map<String, CartItem> getMap() {
		return map;
	}

/*	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
*/
	
	
	public int getTotalCount() {
		List<CartItem> list = getMap2list();
		int totalCount=0;
		for (CartItem item : list) {
			totalCount+=item.getCount();
			
		}
		return totalCount;
	}

/*	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}*/

	public double getTotalAmount() {
		
		
		List<CartItem> list = getMap2list();
		double totalAmount=0;
		BigDecimal bd1 = new BigDecimal(totalAmount+"");
		for (CartItem item : list) {
			BigDecimal bd2 = new BigDecimal(item.getAmount()+"");
			bd1 = new BigDecimal(bd1.add(bd2)+"");
			totalAmount = bd1.doubleValue();
		}
		return totalAmount;
	}

/*	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}*/

	public void addBook(Book book) {
		Integer id = book.getId();
		
		
		if(map.containsKey(id+"")) {
			map.get(id+"").setCount(map.get(id+"").getCount()+1);
			
		}else {
			
			CartItem item = new CartItem();
			item.setBook(book);
			item.setCount(1);
			map.put(id+"", item);
		}

	}

	public void updateById(String bookId, String count) {
			
		CartItem item = map.get(bookId);
		if(item!=null) {
			int c=item.getCount();
			try {
				c = Integer.parseInt(count);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			item.setCount(c);
		}
	}

	public void deleteItemById(String bookId) {
		map.remove(bookId);

	}

	public void deleteAll() {
		map.clear();
		

	}

	public List<CartItem> getMap2list() {
		Collection<CartItem> values = map.values();
		 List<CartItem> arrayList = new ArrayList<CartItem>(values);
		

		return arrayList;
	}

}
