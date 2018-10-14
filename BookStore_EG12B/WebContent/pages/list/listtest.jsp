<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 引入base页面 -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	/* 给查询按钮绑定单击事件，点击提交请求查询价格区间的分页数据 */
	$(function(){
		
		
		$("#btn3").click(function(){
			var id=this.id;
			

			$.ajax({
				//CartServlet?type=addBook&bookId
				url:"${pageContext.request.contextPath}/CartServlet",
				data:{"type":"addBook","bookId":id},
				dataType:"json",
				success: function(res){
					
					var str= JSON.stringify(res);
					var js=JSON.parse(str);
				
					$("#span01").html(str);
					
					$("#div2").html(res.username+js.password);
					
				}
				
				
			});
			
			
			
			
			//return false;//取消A标签的默认跳转行为
			
		});
		
	
	});
</script>
</head>
<body>
<button id="btn"> Ajax</button>
<hr />

<button id="btn2"> Ajax2</button>
<hr />
<button id="btn3"> Ajax3</button>
<hr />

<div  id ="div1" style="width: 400px;height: 400px;background-color: #39987c; border: 1px;" >
			
			
			
			<span id ="span01"> </span>
		</div>
		
		
		<hr />
		<div id ="div2" style="width: 400px;height: 400px;background-color: #39987c; border: 1px;" >

		</div>


</body>
</html>