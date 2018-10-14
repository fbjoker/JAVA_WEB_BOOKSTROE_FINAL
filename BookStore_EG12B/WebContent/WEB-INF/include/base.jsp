<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 动态拼接base标签地址 -->
    <!-- 从请求报文中获取url地址 -->
   
<!-- <base href="http://localhost:8080/BookStore_EG03/"/> -->
<%-- <base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/"/> --%>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath }/"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!--引入css样式   -->
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<!-- 引入jquery文件 -->
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>