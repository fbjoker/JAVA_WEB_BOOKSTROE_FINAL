package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookDao {
 	public int saveBook(Book book); //向数据库中插入一本图书
 	public int deleteBook(String bookId); //根据图书的ID删除一本图书
 	public int updateBook(Book book); //更新一本图书
 	public Book getBookById(String bookId); //根据图书的ID查询一本图书
 	public List<Book> getBookList(); //获取所有的图书
 	
 	public Page<Book> getPage(Page<Book> page);
 	
 	public Page<Book> getPageByPrice(double  minPrice,double maxPrice,Page<Book> page );
 	public Page<Book> getPageBytitle(Page<Book> page,String title);
 	public Page<Book> getPageByAuthor(Page<Book> page,String author);

}
