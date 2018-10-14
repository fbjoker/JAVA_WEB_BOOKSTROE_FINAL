package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookService {
	
 	public boolean saveBook(Book book); //向数据库中插入一本图书
 	public boolean deleteBook(String bookId); //根据图书的ID删除一本图书
 	public boolean updateBook(Book book); //更新一本图书
 	public Book getBookById(String bookId); //根据图书的ID查询一本图书
 	public List<Book> getBookList(); //获取所有的图书
 	
 	
 	public Page<Book> findPage(String pageNumber ,int size);
 	public Page<Book> findPageByPrice(String minPrice,String maxPrice,String pageNumber ,int size);
 	
 	public Page<Book> getPageBytitle(String title,String pageNumber ,int size);
 	public Page<Book> getPageByAuthor(String author,String pageNumber ,int size);
 	
}
