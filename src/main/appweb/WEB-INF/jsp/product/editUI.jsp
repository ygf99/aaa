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
<link href="bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datetimepicker.js"></script>
</head>
<body>
	<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/edit.action">
		
	
		<div class="form-group" hidden>
			<label for="inputEmail3" class="col-sm-2 control-label">商品的id</label>
			<div class="col-sm-10">
				<input  type="text" name="id" class="form-control" id="inputEmail3"
					 value=${product.id }  >
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">标题</label>
			<div class="col-sm-10">
				<input type="text" name="title" class="form-control" id="inputEmail3"
					 value=${product.title }>
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">价格</label>
			<div class="col-sm-10">
				<input type="text" name="price" class="form-control" id="inputEmail3"
					 value=${product.price }>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">商品的描述</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="descs" id="inputPassword3"
					 value=${product.descs }>
			</div>
		</div>
		
		<div class="form-group">
                <label for="dtp_input1" class="col-sm-2 control-label">上架时间</label>
                <div class="input-group date form_datetime col-sm-6" data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1">
                    <input class="form-control" size="16" name="shelftime" type="text" value="<fmt:formatDate value='${product.shelftime }' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
				<input type="hidden" id="dtp_input1" value="" /><br/>
            </div>  
            
             
        <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <div class="checkbox">
	        <label>  
	          <input type="checkbox" name="isshelf" value="1" <c:if test="${product!=null && product.isshelf==1}">checked="checked"</c:if>> 是否下架
	        </label>
	      </div>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <div class="checkbox">
	        <label>
	          <input type="checkbox" name="iscount" value="1" <c:if test="${product!=null &&  product.iscount==1}">checked="checked"</c:if>> 是否折扣
	        </label>
	      </div>
	    </div>
	  </div>
		
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary" >修改</button>
			</div>
		</div>
	</form>

</body>


<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
	
</script>
</html>