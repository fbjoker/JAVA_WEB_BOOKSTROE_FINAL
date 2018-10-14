<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${!empty page.data }">
		<div class="scott">
			<!-- 动态计算  分页页码起始和结束索引 保证每页最多显示5个页码-->
			<c:choose>
				<c:when test="${page.totalPage<5 }">
					<!-- 总页码<5 -->
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${page.totalPage }"></c:set>
				</c:when>
				<c:otherwise>
					<!-- 总页码>=5 -->
					<c:choose>
						<c:when test="${page.pageNumber<=3 }">
							<!-- 当前页码<=3 -->
							<c:set var="begin" value="1"></c:set>
							<c:set var="end" value="5"></c:set>
						</c:when>
						<c:otherwise>
							<!-- 当前页码>3 -->
							<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
							<c:set var="end" value="${page.pageNumber+2 }"></c:set>
							<!-- 判断end值是否超过总页码 -->
							<c:if test="${end>page.totalPage }">
								<c:set var="end" value="${page.totalPage }"></c:set>
								<c:set var="begin" value="${end-4 }"></c:set>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			
			<!-- 动态生成页码 -->
			<a href="${page.path }&pageNumber=${page.pageNumber-1 }"> &lt; </a>
			<c:forEach begin="${pageScope.begin }" end="${pageScope.end }" var="index">
				<c:choose>
					<c:when test="${page.pageNumber==index }">
						<!-- 当前遍历的页码和 分页的页码一样 高亮显示 不让用户点击-->
						<span class="current">${index }</span>
					</c:when>
					<c:otherwise>
						<!-- 其他页码 可以点击 -->
						<a href="${page.path }&pageNumber=${index }">${index }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- BookManagerServlet?type=findPage&pageNumber=1" -->
			<a href="${page.path }&pageNumber=${page.pageNumber+1 }"> &gt; </a>
		共${page.totalPage }页，${page.totalCount }条记录 到第<input
			value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
			id="sendBtn" type="button" value="确定">
		</div>
		<script type="text/javascript">
			$("#sendBtn").click(function(){
				var pageNumber = $("#pn_input").val();
				//window对象：   location属性就是地址栏地址属性，如果修改了location的值，浏览器会自动向新地址发起请求
				//如果在js中使用el，必须写在引号内
				window.location = "${page.path }&pageNumber="+pageNumber;
			});
		
		</script>
</c:if>