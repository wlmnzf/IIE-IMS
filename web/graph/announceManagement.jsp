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
            <img class="brand" src="<%=path%>/img/logo.png" alt="">
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right login-ul">
                <li>
                    <div class="login-info">
                        <div class="media-left media-top">
                            <a href="#">
                                <img class="media-object" src="${_HEAD_URL}" alt="...">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading" data-type="${_TYPE}"><span class="label label-primary">${_TYPE_TEXT}</span></h4>
                            <p class="login-name" data-token="${_TOKEN}" data-username="${_USER_NAME}">${_NAME}</p>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="options">
                        <%--<a class="option" href="#"><span class="glyphicon glyphicon-envelope"></span></a>--%>
                        <a class="option" id="exit" href="<%=path%>/exit">退出</a>
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
                    <li  class="current"><a href="<%=path%>/announceManagement">公告管理</a></li>
                    <li><a href="<%=path%>/announceEditor">公告编辑</a></li>
                </ul>
            </li>
            <li>
                <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>录入</h4>
                <ul class="menu-ul">
                    <li ><a href="<%=path%>/formManage">页面定制</a></li>
                    <%--<li><a href="<%=path%>/formResult">结果管理</a></li>--%>
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
                                <th><span style="font-weight: normal">${announceMent.timeFormat()}</span></th>
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
                        var totalPages = ${page.totalPage};
                        //向上翻页
                        function up(){
                            var up = document.getElementById("up");
                            //alert(currentPage);
                            if(currentPage == 1){
                                alert("当前已是第一页");
                            }
                            else {
                                currentPage -= 1;
                                up.href = "pageManageMent?currentPage=" + currentPage;
                            }
                        }
                        //向下翻页
                        function down(){
                            var down = document.getElementById("down");
                            //setStatus();

                            if(currentPage == ${page.totalPage}){
                                alert("当前已是最后一页");
                            }
                            else {
                                currentPage += 1;
                                down.href = "pageManageMent?currentPage=" + currentPage;
                            }
                    }
                    //点击分页符实现翻页
                    function clickPage(id){
                            var pNum = document.getElementById(id).innerHTML;
                            //alert(pNum);
                            var p = document.getElementById(id);
                            p.href = "pageManageMent?currentPage=" + pNum;
                    }
                    //使所有分页符可见
                    function initLi() {
                            for (var t = 1;t <= 5;t++){
                                document.getElementById("page" + t).style.display = "true";
                            }
                    }
                    </script>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li class="disabled">
                            <span aria-hidden="true">总页数</span>
                            <span>${page.totalPage}</span>
                            </li>
                            <li>
                                <a href="#" aria-label="Previous" onclick="up()" id="up">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="#" id="page1" onclick="clickPage('page1')"></a></li>
                            <li><a href="#" id="page2" onclick="clickPage('page2')"></a></li>
                            <li><a href="#" id="page3" onclick="clickPage('page3')"></a></li>
                            <li><a href="#" id="page4" onclick="clickPage('page4')"></a></li>
                            <li><a href="#" id="page5" onclick="clickPage('page5')"></a></li>
                            <script>
                                //此部分实现动态分页
                                //var totalPages = ${page.totalPage};

                                if(totalPages <= 5){
                                    for(var x = 1; x <= totalPages;t++){
                                        document.getElementById("page" + x).innerHTML = x;
                                    }
                                   for (var i = totalPages + 1;i <= 5;i++)
                                   {
                                       document.getElementById("page" + i).style.display = "none";
                                   }

                                }else {
                                    if (currentPage <= 3){
                                        for(var y = 1; y <= 5;y++){
                                            document.getElementById("page" + y).innerHTML = y;
                                        }
                                    }else {
                                        /*document.getElementById("page1").innerHTML = currentPage - 2;
                                        document.getElementById("page2").innerHTML = currentPage - 1;
                                        document.getElementById("page3").innerHTML = currentPage;*/
                                        if(currentPage == totalPages){
                                            document.getElementById("page1").innerHTML = currentPage - 4;
                                            document.getElementById("page2").innerHTML = currentPage - 3;
                                            document.getElementById("page3").innerHTML = currentPage - 2;
                                            document.getElementById("page4").innerHTML = currentPage - 1;
                                            document.getElementById("page5").innerHTML = currentPage;
                                        }
                                        if(currentPage + 1 == totalPages){
                                            document.getElementById("page1").innerHTML = currentPage - 3;
                                            document.getElementById("page2").innerHTML = currentPage - 2;
                                            document.getElementById("page3").innerHTML = currentPage - 1;
                                            document.getElementById("page4").innerHTML = currentPage;
                                            document.getElementById("page5").innerHTML = currentPage + 1;
                                        }
                                        if(currentPage + 2 <= totalPages){
                                            document.getElementById("page1").innerHTML = currentPage - 2;
                                            document.getElementById("page2").innerHTML = currentPage - 1;
                                            document.getElementById("page3").innerHTML = currentPage;
                                            document.getElementById("page4").innerHTML = currentPage + 1;
                                            document.getElementById("page5").innerHTML = currentPage + 2;
                                        }
                                    }
                                }
                            </script>
                            <script>
                                //此段代码设置分页符选中效果
                                for(var pp = 1 ; pp <= 5 ; pp++){
                                    if(document.getElementById("page" + pp).innerHTML == currentPage){
                                        document.getElementById("page" + pp).style.background = "#BCD2EE";
                                    }
                                }
                            </script>
                            <li>
                                <a href="#" aria-label="Next" onclick="down()" id="down">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>





</body>
</html>
