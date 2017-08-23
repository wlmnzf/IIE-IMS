<%@ page language="java" import="java.util.*,cn.iie.icm.pojo.Node_link" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/extraction/all.css" />
    <script type="text/javascript" charset="utf8" src="<%=path%>/resources/jquery-1.9.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/resources/layer/skin/layer.css" />
    <script type="text/javascript" src="<%=path%>/resources/layer/layer.js?v=3.0.1"></script>
    <script type="text/javascript" charset="utf8" src="<%=path%>/resources/echarts.js"></script>
    <script type="text/javascript" charset="utf8" src="<%=path%>/graph/extraction/extraction.js"></script>
    <style>
    </style>

    <style type="text/css">
        body {
            font-size:14px;
            font-family:微软雅黑;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    var loadindex = layer.load(1, {shade: [0.6, '#293C55'], offset: ["250px"]});
</script>

<div id="container" class="container">
    <div  style="width:100%;height:100%;float: left" id="graph"></div>
</div>

</body>

</html>
