<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- 引入base页面 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>

<script type="text/javascript">

	$(function() {
		var i = 1;
		$("#img").click(function() {

			//this.attr(src,"code.jpg?code="+i++);
			this.src = "code.jpg?code=" + i++;

		});
		
		$("#username").change(function(){
			var name=this.value;
			
			$.ajax({
				url:"UserServlet",
				data:{"type":"userCheck","username":name},
				dataType:"json",
				success:function(res){
					//alert(res);
					if("1"==res){
						$(".errorMsg").text("该用户名可以使用!");
						$("#sub_btn").attr("disabled" , false);
						$("#sub_btn").css("background-color" , "#39987c");
					}else{
						$(".errorMsg").text("该用户已被占用,请换一个!!");
						$("#sub_btn").attr("disabled" , true);
						$("#sub_btn").css("background-color" , "gray");
					}
				}
				
				
				
			});
			
			
			
			
			
		});

	});
</script>

</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								
								<%-- <%
									//获取注册失败后的表单数据回显
									String username = request.getParameter("username");
									String password = request.getParameter("password");
									String email = request.getParameter("email");
									//System.out.println("注册失败的参数："+username+" , "+ password+ " , "+email);
								%> --%>
								<%-- <span class="errorMsg"><%=request.getAttribute("errorMsg")==null?"":request.getAttribute("errorMsg") %></span> --%>
								<span class="errorMsg">${requestScope.errorMsg }</span>
							</div>
							<div class="form">
							<!-- 
								浏览器缓存：
									如果浏览器中发起的get请求地址+参数之前已经访问过，再次使用时浏览器会认为是重复提交的请求，直接使用浏览器缓存，不会向服务器发起请求
								
							 -->
								<form action="UserServlet" method="post">
									<!-- 通过隐藏域携带表单目的参数给servlet -->
									<input type="hidden" name="type" value="regist"/>
									<label>用户名称：</label>
									<!-- 当注册失败时，请求会从UserServlet中转发回来 ， 可以从转发过来的request对象中获取注册失败的参数 -->
									<input id="username" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" 
										value="${param.username }"
									/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email"
										value="${param.email }"
									 />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img id="img" alt="" src="code.jpg" style="float: right; margin-right: 40px; width:90px;height:40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
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