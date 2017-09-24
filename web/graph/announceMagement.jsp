<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: liuzhilei
  Date: 2017/9/7
  Time: 下午4:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta charset="utf-8"/>

    <title>公告管理</title>

    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/admin.css">
    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap-theme.min.css" />

    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/submit.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/customForm_h5.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/jquery.sticky.js"></script>
    <script type="text/javascript"   src="<%=path%>/graph/js/layer/layer.js"></script>


    <link rel="stylesheet" href="<%=path%>/graph/css/customForm_h5.css"/>
</head>
<body>
    <header>
        <div class="container-fluid">
            <div class="navbar-header">
                <img class="brand" src="<%=path%>/graph/img/logo.png" alt="">
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right login-ul">
                    <li>
                        <div class="login-info">
                            <div class="media-left media-top">
                                <a href="#">
                                    <img class="media-object" src="<%=path%>/graph/img/head.jpg" alt="...">
                                </a>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading"><span class="label label-primary">管理员</span></h4>
                                <p class="login-name">会码代码的扫地王大爷</p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="options">
                            <a class="option" href="#"><span class="glyphicon glyphicon-envelope"></span></a>
                            <a class="option" href="#">退出</a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </header>

    <div class="admin-content clearfix">
        <div class="admin-op-list">
            <ul class="menu">
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-tags"></em>公告</h4>
                    <ul class="menu-ul">
                        <li class="current"><a href="<%=path%>/announceMagement">公告管理</a></li>
                        <li><a href="<%=path%>/announceEditor">公告编辑</a></li>
                    </ul>
                </li>
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>录入</h4>
                    <ul class="menu-ul">
                        <li><a href="<%=path%>/formManage">页面定制</a></li>
                        <li><a href="<%=path%>/formResult">结果管理</a></li>
                    </ul>
                </li>
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-user"></em>人员</h4>
                    <ul class="menu-ul">
                        <li><a href="<%=path%>/graph/personalManage.jsp">人员管理</a></li>
                    </ul>
                </li>
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-align-left"></em>分组</h4>
                    <ul class="menu-ul">
                        <li><a href="<%=path%>/graph/groupManage.jsp">分组管理</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="admin-op-panel">
            <div class="panel-content">
                <div class="op-buttons">
                    <script>
                        function addAnc(){
                            window.location.href = "/announceEditor";
                        }
                    </script>
                    <button class="btn btn-default" id="delAll">删除</button>
                    <button class="btn btn-default" id="addNew" onclick="addAnc()">新增</button>
                </div>
                <div class="op-content">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><input class="all" name="event" type="checkbox" value="" /></th>
                            <th>序号</th>
                            <th>标题</th>
                            <th>level</th>
                            <th>发布者</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items = "${announceMents}" var="announceMent" varStatus="id">
                            <tr  data-sh="1">
                                <th scope="row"><input name="event" type="checkbox" value="" /></th>
                                <th><span style="font-weight: normal">${id.index + 1}</span></th>
                                <th>
                                    <a style="text-decoration: none;"  href="showAnc?title=${announceMent.title}">
                                        ${announceMent.title}
                                    </a>
                                </th>

                                <th >
                                    <span style="font-weight: normal;font-family: Arial;color: #00d449;font-size: small;" >${announceMent.level}</span>
                                </th>
                                <th><span style="font-weight: normal">${announceMent.author}</span></th>
                                <th><span style="font-weight: normal">${announceMent.datestamp}</span></th>
                                <th>
                                    <a class="op-choice" name="preview" href="showAnc?title=${announceMent.title}">预览</a>
                                    <a class="op-choice" name="preview" href="/deleteAnc?title=${announceMent.title}">删除</a>
                                    <a class="op-choice" name="del" href="/upperShow?title=${announceMent.title}">置顶</a>
                                </th>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <script>
                        //记录当前页
                        var currentPage = ${page.currentPage};
                        function up(){
                            var up = document.getElementById("up")
                            //alert(currentPage);
                            if(currentPage == 1){
                                alert("当前已是第一页");
                            }
                            else {
                                currentPage -= 1;
                                up.href = "pageManageMent?currentPage=" + currentPage;
                            }

                        }
                        function down(){
                            var down = document.getElementById("down")
                            if(currentPage == ${page.totalPage}){
                                alert("当前已是最后一页");
                            }
                            else {
                                currentPage += 1;
                                down.href = "pageManageMent?currentPage=" + currentPage;
                            }



                    }
                    </script>

                    <script>
                        var num = 5;
                        var totalPage = ${page.totalPage};
                        var startPoint = 1;
                        var endPoint = 5;
                        if(totalPage < num){
                            endPoint = totalPage;
                        }


                    </script>

                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li>
                            <span aria-hidden="true">总页数</span>
                            <span>${page.totalPage}</span>
                            </li>
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            
                                <li><a href="#">${i}</a></li>


                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                        <%--<ul class="pagination">--%>
                            <%--<li>--%>
                                    <%--<span aria-hidden="true">总页数</span>--%>
                                    <%--<span>${page.totalPage}</span>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#"  id="up"  onclick="up()">--%>
                                    <%--<span aria-hidden="true" for="id">上一页</span>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a href="#" id="down" onclick="down()">--%>
                                    <%--<span aria-hidden="true" for="id">下一页</span>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    </nav>
                </div>
            </div>
        </div>
    </div>





</body>
</html>
