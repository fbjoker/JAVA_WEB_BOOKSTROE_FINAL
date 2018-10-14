<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<!-- 引入base页面 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
	<c:choose> 
		<c:when test="${empty requestScope.page.data }">
			<h2 style="color:red; text-align: center; margin-top: 80px;"> 图书卖空了，赶紧添加吧！！！<a href="pages/manager/book_add.jsp">添加图书</a></h2>
			
		</c:when>
		<c:otherwise>
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>	
			
			<c:forEach items="${requestScope.page.data }" var="book">
			<tr>
				<td>${book.title }</td>
				<td>${book.price }</td>
				<td>${book.author }</td>
				<td>${book.sales }</td>
				<td>${book.stock }</td>
				<td><a href="BookManagerServlet?type=findBook&bookId=${book.id }">修改</a></td>
				<td><a href="BookManagerServlet?type=deleteBook&bookId=${book.id }">删除</a></td>
			</tr>	
			</c:forEach>	
			
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
			</tr>	
		</table>
	</c:otherwise>
	</c:choose>
	</div>
	
	<%@ include file="/WEB-INF/include/nav.jsp" %>
	
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>