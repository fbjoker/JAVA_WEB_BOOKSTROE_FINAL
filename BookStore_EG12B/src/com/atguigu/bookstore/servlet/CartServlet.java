package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       BookService bs=new BookServiceImpl();
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = bs.getBookById(bookId);
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if(cart==null) {
			cart = new Cart();
			
			session.setAttribute("cart", cart);
		}
		
		cart.addBook(book);
		session.setAttribute("title", book.getTitle());
		
		String title= book.getTitle();
		int totalCount=cart.getTotalCount();
		
		//MAP 或者LIST都可以转换成为json
		Map map= new HashMap();
		map.put("title", title);
		map.put("totalCount", totalCount);
		
		 Gson gson = new Gson(); 
		 
		 String json = gson.toJson(map);
		 
		 
		 response.getWriter().write(json);
		 System.out.println(json);
		
		
		
		
		
		//使用ajax方式不用再跳转
		//response.sendRedirect(request.getHeader("referer"));
		
	}
	
	
	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		String bookId = request.getParameter("bookId");
		HttpSession session = request.getSession();
		
		Cart cart = (Cart) session.getAttribute("cart");
		
		cart.deleteItemById(bookId);
		
		response.sendRedirect(request.getHeader("referer"));
		
		
	}
	
	
	
	protected void clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cart.deleteAll();
		response.sendRedirect(request.getHeader("referer"));

	}
	
	
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String count = request.getParameter("count");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		cart.updateById(bookId, count);
		response.sendRedirect(request.getHeader("referer"));
		
		
	}


}
