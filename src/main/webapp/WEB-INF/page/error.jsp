<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>异常界面</title>

<link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">

<script src="js/jquery.min.js"></script>
<script src="components/bootstrap/js/bootstrap.min.js"></script>




</head>

<body>

<div class="container "   style="margin-top:100px">
 
<div class="panel panel-danger">
  <div class="panel-heading">
    <h3 class="panel-title">访问资源异常</h3>
  </div>
  <div class="panel-body">
          服务器返回信息:    ${message }   <br>
          
          哥们别乱来！
  </div>
</div>





</div>

 
	
	</body>


</html>
