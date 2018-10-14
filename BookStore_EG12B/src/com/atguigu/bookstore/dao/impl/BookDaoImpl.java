package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao {

	@Override
	public int  saveBook(Book book) {
		String sql="insert into bs_book (title,author,price,sales,stock,img_path) "
				+ " values(?,?,?,?,?,?)";
		 return update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
		

	}

	@Override
	public int  deleteBook(String bookId) {
		String sql="delete from bs_book where id=?";
		return update(sql, bookId);

	}

	@Override
	public int  updateBook(Book book) {
		String sql="update bs_book set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
		return update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
	}

	@Override
	public Book getBookById(String bookId) {
		String sql="select id,title,author,price,sales,stock,img_path imgPath "
				+ "from bs_book where id=?";
		
		return getBean(Book.class, sql, bookId);
	}

	@Override
	public List<Book> getBookList() {
		String sql="select id,title,author,price,sales,stock,img_path imgPath "
				+ "from bs_book ";
		
		return getBeanList(Book.class, sql);
	}

	@Override
	public Page<Book> getPage(Page<Book> page) {
		String countsql="select count(*) from bs_book";
		long countall = (long)getScalar(countsql);
		int totalCount=(int) countall;
		page.setTotalCount(totalCount);
		
		
		String sql="select id,title,author,price,sales,stock,img_path imgPath "
				+ "from bs_book limit ?,?";
		List<Book> list = getBeanList(Book.class, sql, page.getIndex(),page.getSize());
		page.setData(list);
		
		
		return page;
	}

	@Override
	public Page<Book> getPageByPrice(double minPrice, double maxPrice, Page<Book> page) {
		String countsql="select count(*) from bs_book where price between ? and ?";
		long countall = (long)getScalar(countsql,minPrice,maxPrice);
		int totalCount=(int) countall;
		page.setTotalCount(totalCount);
		
		
		String sql="select id,title,author,price,sales,stock,img_path imgPath "
				+ "from bs_book where price between ? and ? limit ?,?";
		List<Book> list = getBeanList(Book.class, sql,minPrice,maxPrice, page.getIndex(),page.getSize());
		page.setData(list);
		return page;
	}

	@Override
	public Page<Book> getPageBytitle(Page<Book> page, String title) {
		//String countsql="select count(*) from bs_book where title LIKE '% ? %'";
		String countsql="select count(*) from bs_book where title LIKE ?";
		 title= "%"+title+"%";
		long countall = (long)getScalar(countsql,title);
		int totalCount=(int) countall;
		page.setTotalCount(totalCount);
		
		String sql="select id,title,author,price,sales,stock,img_path imgPath "
				+ "from bs_book where title LIKE ? limit ?,?";
		
		 List<Book> list = getBeanList(Book.class, sql, title,page.getIndex(),page.getSize()); 
		 page.setData(list);
		
		return page;
	}

	@Override
	public Page<Book> getPageByAuthor(Page<Book> page, String author) {
		String countsql="select count(*) from bs_book where author LIKE ?";
		author= "%"+author+"%";
		long countall = (long)getScalar(countsql,author);
		int totalCount=(int) countall;
		page.setTotalCount(totalCount);
		
		String sql="select id,title,author,price,sales,stock,img_path imgPath "
				+ "from bs_book where author LIKE ? limit ?,?";
		
		 List<Book> list = getBeanList(Book.class, sql, author,page.getIndex(),page.getSize()); 
		 page.setData(list);
		
		return page;
	}

}
