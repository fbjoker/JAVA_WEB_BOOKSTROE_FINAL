<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/include/base.jsp" %>

<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

<link type="text/css" rel="stylesheet" href="static/css/style.css">
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>

	<div id="main">
	
	
	<c:choose>
	
	<c:when test="${empty sessionScope.list }">
	<h2> 订单为空 </h2>
	</c:when>
	
	
	<c:otherwise>
			<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>
				<c:forEach items="${sessionScope.list }" var="order">
					<tr>
						<td>${order.orderId }</td>
						<td>${order.createTime}</td>
						<td>${order.totalAmount}</td>
						
						
						<c:choose>
						<c:when test="${order.status==0}"><td><a >未发货</a></td></c:when>
						<c:when test="${order.status==1}"><td><a href="OrderServlet?type=takeGoods&orderId=${order.orderId }">确认收货</a></td></c:when>
						<c:when test="${order.status==2}"><td><a >交易完成</a></td></c:when>
						
						
						
						
						
						</c:choose>
						<td><a href="#">查看详情</a></td>
					</tr>
				</c:forEach>
				
				</table>
		</c:otherwise>
	</c:choose>

	<c:remove var="list" scope="session"/>	
	

	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2018 </span>
	</div>
</body>
</html>