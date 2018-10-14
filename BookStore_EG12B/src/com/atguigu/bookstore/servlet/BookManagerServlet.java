package com.atguigu.bookstore.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bs = new BookServiceImpl();

	protected void bookList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> list = bs.getBookList();
		request.setAttribute("list", list);

		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

	protected void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//1、创建解析工厂对象
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				
				//2、创建解析器对象 
				ServletFileUpload fileUpload = new ServletFileUpload(factory);
				
				
				
				
				try {
					List<FileItem> list = fileUpload.parseRequest(request);
					
					for (FileItem fileItem : list) {
						//根据表单项的类型分别处理
						if(fileItem.isFormField()) {
							
							Book book = WebUtils.parm2Bean(new Book(), request);
							//文本表单项
							System.out.println("表单项name属性值："+fileItem.getFieldName());
							System.out.println("表单项的value属性值："+fileItem.getString());
						}else {
							//如果上传文件的大小为0，没有必要保存到本地
							if(fileItem.getSize()>0) {
								//获取当前项目根目录下保存文件的目录路径
								ServletContext context = request.getServletContext();
								String uploadPath = context.getRealPath("/img");
								File file = new File(uploadPath);
								if(!file.exists()) {
									file.mkdirs();//创建路径下的所有文件夹
								}
								//System.out.println(uploadPath);
								
								//文件表单项： 需要保存到服务器中
								/*System.out.println("表单项内容类型："+fileItem.getContentType());
								System.out.println("上传的文件的名称："+fileItem.getName());
								System.out.println("内容大小："+fileItem.getSize());*/
								//后上传的同名文件会覆盖之前的，需要给文件指定唯一的文件名
								//String fileName = System.currentTimeMillis()+"_"+UUID.randomUUID().toString().replace("-", "")+"_"+fileItem.getName();
								File photofile = new File(file, fileItem.getName());
								//File file = new File("C:\\Users\\Administrator\\Desktop\\"+fileName);
								//用户上传的文件，一般保存在专门的文件服务器中，或者当前项目的指定文件夹内，以便用户还可以访问
								
									fileItem.write(photofile);
								//调用部件对象的方法将上传的文件写入到服务器硬盘指定路径下
							}
							
							
						}
						
						
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
		Book book = WebUtils.parm2Bean(new Book(), request);
		/*String parameter = request.getParameter("imgPath");
		System.out.println(parameter);*/
		bs.saveBook(book);
		System.out.println("add");
		response.sendRedirect(request.getContextPath() + "/BookManagerServlet?type=bookList");

	}

	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String bookId = request.getParameter("bookId");
		bs.deleteBook(bookId);

		String ref = request.getHeader("referer");
		response.sendRedirect(ref);

	}

	protected void editBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = WebUtils.parm2Bean(new Book(), request);

		boolean updateBook = bs.updateBook(book);
		String ref = request.getParameter("ref");
		response.sendRedirect(ref);

	}

	protected void findBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");

		Book book = bs.getBookById(bookId);

		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);

	}
	
	
	protected void findPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size=4;
		Page<Book> page = bs.findPage(pageNumber, size);
		page.setPath(WebUtils.getPath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
		
	}

}
