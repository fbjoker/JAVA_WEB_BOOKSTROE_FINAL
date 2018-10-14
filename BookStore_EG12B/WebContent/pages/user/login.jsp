<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<!-- 引入base页面 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<%-- <%
								//获取错误消息并显示
								//判断用户是第一次打开登录页面还是登录失败转发回来的
								//如果request域中有错误消息，代表登录失败回来的，如果没有用户第一次访问
								String  errorMsg = (String)request.getAttribute("errorMsg");
								if(errorMsg==null){
									errorMsg = "请输入用户名和密码";
								}
							%>
								<span class="errorMsg"><%=errorMsg %></span> --%>
							<div class="msg_cont">
								<b></b>
								<!-- 从域中获取错误消息显示 -->
								<span class="errorMsg">${empty errorMsg?"请输入用户名和密码":errorMsg }</span>
							</div>
							<div class="form">
								<!-- <form action="../../LoginServlet"> -->
								<!-- http://localhost:8080 -->
								<form action="UserServlet" method="post">
									<!-- 通过隐藏表单项携带参数交给服务器，告诉服务器表单提交的类型 -->
									<input type="hidden" name="type" value="login"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>