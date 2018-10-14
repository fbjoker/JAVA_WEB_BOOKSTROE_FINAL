<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 引入base页面 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>

	<!-- 转发到BookClientServlet查询首页的分页数据 -->
	<jsp:forward page="/BookClientServlet?type=findPage&pageNumber=1"></jsp:forward>

</body>
</html>