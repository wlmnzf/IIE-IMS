<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="utf-8"/>
    <%--<link rel="Shortcut Icon" href="<%=path%>/graph/images/logo.jpg" type="image/x-icon">--%>
    <title>信息录入系统</title>

</head>


<body>
</body>
<a href="<%=path%>/formResult">结果管理</a>
<a href="<%=path%>/formManage">表单管理页面</a> </br>
<a href="<%=path%>/graph/personalManage.jsp">人员管理页面</a> </br>
<a href="<%=path%>/graph/groupManage.jsp">分组管理页面</a>
</html>
