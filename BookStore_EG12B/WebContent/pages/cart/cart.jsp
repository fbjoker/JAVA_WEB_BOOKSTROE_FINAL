<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<!-- 引入base页面 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
$(function(){
	
	$(".delA").click(function(){
		var name=$(this).parents("tr").children("td").first().text();
		
		if(!confirm("确定要删除"+name+"吗?")){
			return false;
		}
	});
	
	
	$(".count").change(function(){
		var count = this.value;
		var id = this.id;
		/* alert(id);
		alert(count); */
		window.location="CartServlet?type=update&count="+count+"&bookId="+id;
		
	});
	
	
	
});


</script>


</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
	<c:choose>
	<c:when test="${empty sessionScope.cart.map }">
	<h2 style="text-align: center; color: red;border-top: 40px;">购物车位空</h2>
	</c:when>
	<c:otherwise>
	
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>封面</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			
		<c:forEach items="${sessionScope.cart.map2list }" var="item">
			<tr>
				<td>${item.book.title }</td>
				<td><img src="${pageContext.request.contextPath }${item.book.imgPath }" style="width: 80px;"/></td>
				<td><input class="count" id ="${item.book.id }" style="width: 30px;text-align: center;" value="${item.count }"/></td>
				<td>${item.book.price }</td>
				<td>${item.amount }</td>
				<td><a class="delA" href="CartServlet?type=deleteBook&bookId=${item.book.id }">删除</a></td>
			</tr>	
			
	</c:forEach>		
			
		</table>
		
	<%-- 	<%@ include file="/WEB-INF/include/nav.jsp" %> --%>
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount }</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalAmount }</span>元</span>
			<span class="cart_span"><a href="CartServlet?type=clear">清空购物车</a></span>
			<span class="cart_span"><a href="OrderServlet?type=checkOut">去结账</a></span>
		</div>
			</c:otherwise>	
		</c:choose>
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>