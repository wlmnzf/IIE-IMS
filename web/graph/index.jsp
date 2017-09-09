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
<%--<a href="<%=path%>/customForm.do">自定义表单页面a</a>--%>
<a href="<%=path%>/customForm">自定义表单页面a</a> </br>
<a href="<%=path%>/graph/personalManage.jsp">人员管理页面a</a> </br>
<a href="<%=path%>/graph/groupManage.jsp">分组管理页面a</a>
</html>

