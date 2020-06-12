<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
  
 <form name="myForm" class="form-inline" style="text-align:center;" method="post" action="${pageContext.request.contextPath}/batchEdit.action">
  <div class="form-group">
  </div>
    <div class="table-responsive">
	<table class="table table-condensed">
     <caption>显示所有的商品的信息</caption>
      <thead>
        <tr>
          <th>id</th>
          <th>标题</th>
          <th>价格</th>
          <th>介绍</th>
          <th>日期</th>
          
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${productlist }" var="p" varStatus="status">
	        
	        <tr>
	          <td scope="row"><input type="checkbox" name="productListCustomer[${status.index }].id"  value="${p.id }" checked></td>
	          <td><input type="text" name="productListCustomer[${status.index }].title" value="${p.title }"></td>
	          <td><input type="text" name="productListCustomer[${status.index }].price" value="${p.price }"></td>
	          <td><input type="text" name="productListCustomer[${status.index }].descs" value="${p.descs }"></td>
	          <td>
	             <input type="text" name="productListCustomer[${status.index }].shelftime" value="<fmt:formatDate value='${p.shelftime }' pattern='yyyy-MM-dd HH:mm:ss'/>">
	          </td>
	        </tr>
       </c:forEach>
      </tbody>
    </table>
  </div>
  <input type="hidden" name="startPage" value="${productQuryVo.startPage }">
  <input type="hidden" name="productCustomer.title" value="${productQuryVo.productCustomer.title }">
  <input type="submit" value="批量修改" class="btn btn-info">
  </form>
  
</body>
</html>