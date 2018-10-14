package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	BookDao bd= new BookDaoImpl();

	@Override
	public boolean saveBook(Book book) {
		
		return bd.saveBook(book)>0;
	}

	@Override
	public boolean deleteBook(String bookId) {
		return bd.deleteBook(bookId)>0;
	}

	@Override
	public boolean updateBook(Book book) {
		return bd.updateBook(book)>0;
	}

	@Override
	public Book getBookById(String bookId) {
		return bd.getBookById(bookId);
	}

	@Override
	public List<Book> getBookList() {
		
		return bd.getBookList();
	}

	@Override
	public Page<Book> findPage(String pageNumber, int size) {
		Page<Book> page=new Page();
		int num=1;
		try {
			num = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		page.setPageNumber(num);
		page.setSize(size);
		 
		return bd.getPage(page);
	}

	@Override
	public Page<Book> findPageByPrice(String minPrice, String maxPrice, String pageNumber, int size) {
		Page<Book> page=new Page();
		int num=1;
		try {
			num = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		double min=0;
		try {
			min = Double.parseDouble(minPrice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		double max=9999;
		try {
			max = Double.parseDouble(maxPrice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		page.setPageNumber(num);
		page.setSize(size);
		 
		return bd.getPageByPrice(min, max, page);
	}

	@Override
	public Page<Book> getPageBytitle(String title, String pageNumber, int size) {
		Page<Book> page=new Page();
		int num=1;
		try {
			num = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		page.setPageNumber(num);
		page.setSize(size);
		
		
		
		return bd.getPageBytitle(page, title);
	}

	@Override
	public Page<Book> getPageByAuthor(String author, String pageNumber, int size) {
		Page<Book> page=new Page();
		int num=1;
		try {
			num = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		page.setPageNumber(num);
		page.setSize(size);
		return bd.getPageByAuthor(page, author);
	}

	

}
