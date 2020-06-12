<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
 
	<form name="myForm" class="form-inline" style="text-align: center;" method="post"
		action="${pageContext.request.contextPath}/list2.action">
		<div class="form-group">
			<label for="exampleInputName2">请输入标题</label> <input type="text"
				class="form-control" name="productCustomer.title"
				value="${productQuryVo !=null && productQuryVo.productCustomer!=null && productQuryVo.productCustomer.title!=null?productQuryVo.productCustomer.title:''}"
				id="exampleInputName2" placeholder="请输入标题">
		</div>
		<button id="btnQuery" type="button" class="btn btn-danger">查询</button>
		<button id="btnBathDelete" type="button" class="btn btn-danger">批量删除</button>
		<button id="btnBathEdit" type="button" class="btn btn-info">批量编辑</button>
	  <a href="${pageContext.request.contextPath}/addUI.action" class="btn btn-primary">新增</a>

	<div class="table-responsive">
		<table class="table table-condensed">
			<caption>显示所有的商品的信息</caption>
			<thead>
				<tr>
					<th>id</th>
					<th>标题</th>
					<th>价格</th>
					<th>描述</th>
					<th>上架时间</th>

					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="p" items="${pageinfo.list }">
					<tr>
						<th scope="row"><input type="checkbox" name="ids" value="${p.id }"></th>
						<td>${p.title }</td>
						<td>${p.price }</td>
						<td>${p.descs }</td>
						<td><fmt:formatDate value="${p.shelftime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="${pageContext.request.contextPath}/editUI.action?id=${p.id }"
							class="btn btn-primary">修改</a> <a
							href="${pageContext.request.contextPath}/delete.action?id=${p.id }"
							class="btn btn-danger">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>

		<nav aria-label="Page navigation" style="text-align: center;">
			<ul class="pagination">
				<li><a
					href="javascript:goToPage(${pageinfo.prePage})"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach var="n" begin="1" end="${pageinfo.pages}">
					<c:if test="${pageinfo.pageNum==n }">
						<li class="active"><a href="javascript:void(0);">${n }<span
								class="sr-only">(current)</span></a></li>
					</c:if>
					<c:if test="${pageinfo.pageNum!=n  }">
						<li><a
							href="javascript:goToPage(${n})">${n}</a></li>
					</c:if>
				</c:forEach>


				<li><a
					href="javascript:goToPage(${pageinfo.nextPage})"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		</div>
		</form>
		<script type="text/javascript">
		function goToPage(pg){
			window.alert("1111")
			var _input = $("<input type='hidden' name='startPage' value='"+pg+"'>");
			$("form[name='myForm']").append(_input);
			document.myForm.submit();
			<!--$("form[name='myForm']").get(0).submit(); -->
		}
		
		$("#btnQuery").click(function(){
			window.alert("1111")
			var _form = $("form[name='myForm']");
			_form.attr("action","${pageContext.request.contextPath}/list.action");
			document.myForm.submit();
		});
		
		
		 $("#btnBathDelete").click(function(){
			var len = $("input[name='ids']:checked").length;
		    	 if(len>0){
		    		 var _form= $("form[name='myForm']");
		    	 
		    	 _form.attr("action","${pageContext.request.contextPath}/bathDelete.action");
		    	  var _input=$("<input type='hidden' name='startPage' value='${pageinfo.pageNum}'>");
		    	 _form.append(_input);
		    	 document.myForm.submit();
		     }else{
		    	 alert("至少选择一行");
		    	 }
		     });
		
		 $("#btnBathEdit").click(function(){
			var len = $("input[name='ids']:checked").length;
		    	 if(len>0){
		    		 var _form= $("form[name='myForm']");
		    	 
		    	 _form.attr("action","${pageContext.request.contextPath}/btnBathEditUI.action");
		    	  var _input=$("<input type='hidden' name='startPage' value='${pageinfo.pageNum}'>");
		    	 _form.append(_input);
		    	 document.myForm.submit();
		     }else{
		    	 alert("至少选择一行");
		    	 }
		     });

		</script>
		
</body>
</html>