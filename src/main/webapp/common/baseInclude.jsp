<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("path",path);
String resource_path=path;
request.setAttribute("resource_path",resource_path);
%>

<script type="text/javascript">
var PATH='${path}';
var RESOURCE_PATH='${resource_path}';
</script>