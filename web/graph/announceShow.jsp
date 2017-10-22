<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<script>
    //动态分页函数
    function autoPage(page1 , page2 , page3 , page4 , page5 , totalPages , currentPage) {
        currentPage = parseInt(currentPage,10);
        totalPages = parseInt(totalPages,10);
        if(totalPages <= 5){
            switch (totalPages){
                case 1:
                    document.getElementById(page1).innerHTML = 1;
                    document.getElementById(page2).style.display = "none";
                    document.getElementById(page3).style.display = "none";
                    document.getElementById(page4).style.display = "none";
                    document.getElementById(page5).style.display = "none";
                    break;
                case 2:
                    document.getElementById(page1).innerHTML = 1;
                    document.getElementById(page2).innerHTML = 2;
                    document.getElementById(page3).style.display = "none";
                    document.getElementById(page4).style.display = "none";
                    document.getElementById(page5).style.display = "none";
                    break;
                case 3:
                    document.getElementById(page1).innerHTML = 1;
                    document.getElementById(page2).innerHTML = 2;
                    document.getElementById(page3).innerHTML = 3;
                    document.getElementById(page4).style.display = "none";
                    document.getElementById(page5).style.display = "none";
                    break;
                case 4:
                    document.getElementById(page1).innerHTML = 1;
                    document.getElementById(page2).innerHTML = 2;
                    document.getElementById(page3).innerHTML = 3;
                    document.getElementById(page4).innerHTML = 4;
                    document.getElementById(page5).style.display = "none";
                    break;
                case 5:
                    document.getElementById(page1).innerHTML = 1;
                    document.getElementById(page2).innerHTML = 2;
                    document.getElementById(page3).innerHTML = 3;
                    document.getElementById(page4).innerHTML = 4;
                    document.getElementById(page5).innerHTML = 5;
                    break;
            }
        }else {

            if (currentPage <= 3){
                document.getElementById(page1).innerHTML = 1;
                document.getElementById(page2).innerHTML = 2;
                document.getElementById(page3).innerHTML = 3;
                document.getElementById(page4).innerHTML = 4;
                document.getElementById(page5).innerHTML = 5;
            }else {
                if(currentPage == totalPages){
                    document.getElementById(page1).innerHTML = currentPage - 4;
                    document.getElementById(page2).innerHTML = currentPage - 3;
                    document.getElementById(page3).innerHTML = currentPage - 2;
                    document.getElementById(page4).innerHTML = currentPage - 1;
                    document.getElementById(page5).innerHTML = currentPage;
                }
                if(currentPage + 1 == totalPages){
                    document.getElementById(page1).innerHTML = currentPage - 3;
                    document.getElementById(page2).innerHTML = currentPage - 2;
                    document.getElementById(page3).innerHTML = currentPage - 1;
                    document.getElementById(page4).innerHTML = currentPage;
                    document.getElementById(page5).innerHTML = currentPage + 1;
                }
                var sum = currentPage + 2;

                if(currentPage + 2 <= totalPages){
                    document.getElementById(page1).innerHTML = currentPage - 2;
                    document.getElementById(page2).innerHTML = currentPage - 1;
                    document.getElementById(page3).innerHTML = currentPage;
                    document.getElementById(page4).innerHTML = currentPage + 1;
                    document.getElementById(page5).innerHTML = currentPage + 2;
                }
            }
        }
    }


</script>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0">
    <meta charset="utf-8"/>
    <!--<link rel="Shortcut Icon" href="images/logo.jpg" type="image/x-icon">-->
    <title>信息录入系统</title>

    <link rel="stylesheet" href="<%=path%>/graph/css/bootstrap/bootstrap.min.css" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/all-common.css">
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/user-inform.css">
    <script src="<%=path%>/graph/js/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/graph/js/bootstrap/bootstrap.min.js"></script>
    <!--<script type="text/javascript" src="js/index.js"></script>-->
</head>

<script>
    $(document).ready(function(){
        $("#urgentT a").click(function(e){
            e.preventDefault()
            var type=($(".panel-content >.tab-content >.active ul >.active a").attr("href"));
//            try {
                $(this).tab('show')
//            }
//            catch(e)
//            {}
            if(type=="#activities-2"||type=="#activities-1")
                $('#urgent a[href="#activities-2"]').tab('show') // Select tab by name
            else
                $('#urgent a[href="#informs-2"]').tab('show') // Select tab by name
        })

        $("#timeT a").click(function(e){
            e.preventDefault()
            var type=($(".panel-content >.tab-content >.active ul >.active a").attr("href"));
            $(this).tab('show')
            if(type=="#activities-2"||type=="#activities-1")
                $('#time a[href="#activities-1"]').tab('show') // Select tab by name
            else
                $('#time a[href="#informs-1"]').tab('show') // Select tab by name
        })
    })
    </script>

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



    <div class="user-collapse">
        <div class="container-fluid">
            <div class="inform">
                <div class="user-welcome">
                    <strong>welcome,</strong><span   data-token="${_TOKEN}" data-username="${_USER_NAME}">${_NAME}</span>
                </div>
                <div class="options">
                    <a class="option" href="#"><span class="glyphicon glyphicon-envelope"></span></a>
                    <a class="option" href="<%=path%>/exit">退出</a>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="admin-content clearfix">
    <div class="admin-op-list">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-menu">
                <i class="glyphicon glyphicon-th-list"></i>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="main-menu" aria-expanded="false">
            <ul class="menu">
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-tags"></em>公告</h4>
                    <ul class="menu-ul">
                        <li><a href="<%=path%>/announceShow"  class="active">公告</a></li>
                    </ul>
                </li>
                <li>
                    <h4 class="menu-title"><em class="glyphicon glyphicon-inbox"></em>信息录入</h4>
                    <ul class="menu-ul">
                        <li><a href="<%=path%>/inManage">信息录入</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <div class="admin-op-panel">
        <div class="panel-content">
            <ul class="nav nav-tabs cate-tabs" role="tablist">
                <li  id="urgentT" role="presentation"><a href="#urgent" aria-controls="profile" role="tab" data-toggle="tab">紧急</a></li>
                <li  id="timeT" role="presentation" class="active"><a href="#time" aria-controls="home" role="tab" data-toggle="tab">时间</a></li>
            </ul>

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="time">
                    <ul class="nav nav-tabs underline-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#informs-1" aria-controls="home" role="tab" data-toggle="tab">通知</a></li>
                        <li role="presentation"><a href="#activities-1" aria-controls="profile" role="tab" data-toggle="tab">活动</a></li>
                    </ul>

                    <!-- Tab panes -->
             <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="informs-1">
                            <div class="table-responsive">
                                <table class="table table-striped" >
                                    <tbody id="tb1">
                                    <c:forEach items="${anc_c_t_info}" var="tInfo" >
                                        <tr>
                                            <c:if test="${tInfo.stickly == 1}">
                                                <td><span class="label label-warning">置顶</span></td>
                                            </c:if>
                                            <c:if test="${tInfo.stickly == 0}">
                                                <c:if test="${tInfo.level == '紧急'}">
                                                    <td><span class="label label-danger">${tInfo.level}</span></td>
                                                </c:if>
                                                <c:if test="${tInfo.level == '普通'}">
                                                    <td><span class="label label-info">${tInfo.level}</span></td>
                                                </c:if>
                                            </c:if>

                                            <td>
                                                <p><a href="showIndex?c_title=${tInfo.title}"><span class="event-title">${tInfo.title}</span></a></p>
                                            </td>
                                            <td>${tInfo.author}</td>
                                            <td>${tInfo.timeFormat()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous" onclick="up(1)">&laquo;
                                            <%--<span aria-hidden="true">&laquo;</span>--%>
                                        </a>
                                    </li>
                                    <li id="pg_t_info_p1"><a href="#" id="t_info_p1" onclick="turnPage(1,'t_info_p1')"></a></li>
                                    <li id="pg_t_info_p2"><a href="#" id="t_info_p2" onclick="turnPage(1,'t_info_p2')"></a></li>
                                    <li id="pg_t_info_p3"><a href="#" id="t_info_p3" onclick="turnPage(1,'t_info_p3')"></a></li>
                                    <li id="pg_t_info_p4"><a href="#" id="t_info_p4" onclick="turnPage(1,'t_info_p4')"></a></li>
                                    <li id="pg_t_info_p5"><a href="#" id="t_info_p5" onclick="turnPage(1,'t_info_p5')"></a></li>
                                    <li>
                                        <a href="#" aria-label="Next" onclick="next(1)">&raquo;
                                            <%--<span aria-hidden="true" onclick="nextPage(1)">&raquo;</span>--%>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>


                        <div role="tabpanel" class="tab-pane" id="activities-1">
                            <div class="table-responsive">
                                <table class="table table-striped" >
                                    <tbody id="tb2">
                                    <c:forEach items="${anc_c_t_act}" var="tInfo" >
                                        <tr>
                                            <c:if test="${tInfo.stickly == 1}">
                                                <td><span class="label label-warning">置顶</span></td>
                                            </c:if>
                                            <c:if test="${tInfo.stickly == 0}">
                                                <c:if test="${tInfo.level == '紧急'}">
                                                    <td><span class="label label-danger">${tInfo.level}</span></td>
                                                </c:if>
                                                <c:if test="${tInfo.level == '普通'}">
                                                    <td><span class="label label-info">${tInfo.level}</span></td>
                                                </c:if>
                                            </c:if>

                                            <td>
                                                <p><a href="showIndex?c_title=${tInfo.title}"><span class="event-title">${tInfo.title}</span></a></p>
                                            </td>
                                            <td>${tInfo.author}</td>
                                            <td>${tInfo.timeFormat()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>





                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous" onclick="up(2)">&laquo;
                                            <%--<span aria-hidden="true">&laquo;</span>--%>
                                        </a>
                                    </li>
                                    <li id="pg_t_act_p1"><a href="#" id="t_act_p1" onclick="turnPage(2,'t_act_p1')"></a></li>
                                    <li id="pg_t_act_p2"><a href="#" id="t_act_p2" onclick="turnPage(2,'t_act_p2')"></a></li>
                                    <li id="pg_t_act_p3"><a href="#" id="t_act_p3" onclick="turnPage(2,'t_act_p3')"></a></li>
                                    <li id="pg_t_act_p4"><a href="#" id="t_act_p4" onclick="turnPage(2,'t_act_p4')"></a></li>
                                    <li id="pg_t_act_p5"><a href="#" id="t_act_p5" onclick="turnPage(2,'t_act_p5')"></a></li>
                                    <li>
                                        <a href="#" aria-label="Next" onclick="next(2)">&raquo;
                                            <%--<span aria-hidden="true">&raquo;</span>--%>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>


                <div role="tabpanel" class="tab-pane" id="urgent">
                    <ul class="nav nav-tabs underline-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#informs-2" aria-controls="home" role="tab" data-toggle="tab">通知</a></li>
                        <li role="presentation"><a href="#activities-2" aria-controls="profile" role="tab" data-toggle="tab">活动</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="informs-2">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody id="tb3">


                                    <c:forEach items="${anc_c_u_info}" var="uInfo" >
                                        <tr>
                                            <c:if test="${uInfo.stickly == 1}">
                                                <td><span class="label label-warning">置顶</span></td>
                                            </c:if>
                                            <c:if test="${uInfo.stickly == 0}">
                                                <c:if test="${uInfo.level == '紧急'}">
                                                    <td><span class="label label-danger">${uInfo.level}</span></td>
                                                </c:if>
                                                <c:if test="${uInfo.level == '普通'}">
                                                    <td><span class="label label-info">${uInfo.level}</span></td>
                                                </c:if>
                                            </c:if>

                                            <td>
                                                <p><a href="showIndex?c_title=${uInfo.title}"><span class="event-title">${uInfo.title}</span></a></p>
                                            </td>
                                            <td>${uInfo.author}</td>
                                            <td>${uInfo.timeFormat()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous" onclick="up(3)">&laquo;
                                            <%--<span aria-hidden="true">&laquo;</span>--%>
                                        </a>
                                    </li>
                                    <li id="pg_u_info_p1"><a href="#" id="u_info_p1" onclick="turnPage(3,'u_info_p1')"></a></li>
                                    <li id="pg_u_info_p2"><a href="#" id="u_info_p2" onclick="turnPage(3,'u_info_p2')"></a></li>
                                    <li id="pg_u_info_p3"><a href="#" id="u_info_p3" onclick="turnPage(3,'u_info_p3')"></a></li>
                                    <li id="pg_u_info_p4"><a href="#" id="u_info_p4" onclick="turnPage(3,'u_info_p4')"></a></li>
                                    <li id="pg_u_info_p5"><a href="#" id="u_info_p5" onclick="turnPage(3,'u_info_p5')"></a></li>
                                    <li>
                                        <a href="#" aria-label="Next" onclick="next(3)">&raquo;
                                            <%--<span aria-hidden="true">&raquo;</span>--%>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="activities-2">
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <tbody id="tb4">
                                    <c:forEach items="${anc_c_u_act}" var="uInfo" >
                                        <tr>
                                            <c:if test="${uInfo.stickly == 1}">
                                                <td><span class="label label-warning">置顶</span></td>
                                            </c:if>
                                            <c:if test="${uInfo.stickly == 0}">
                                                <c:if test="${uInfo.level == '紧急'}">
                                                    <td><span class="label label-danger">${uInfo.level}</span></td>
                                                </c:if>
                                                <c:if test="${uInfo.level == '普通'}">
                                                    <td><span class="label label-info">${uInfo.level}</span></td>
                                                </c:if>
                                            </c:if>

                                            <td>
                                                <p><a href="showIndex?c_title=${uInfo.title}"><span class="event-title">${uInfo.title}</span></a></p>
                                            </td>
                                            <td>${uInfo.author}</td>
                                            <td>${uInfo.timeFormat()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination">
                                    <li>
                                        <a href="#" aria-label="Previous" onclick="up(4)">&laquo;
                                            <%--<span aria-hidden="true">&laquo;</span>--%>
                                        </a>
                                    </li>
                                    <li id="pg_u_act_p1"><a href="#" id="u_act_p1" onclick="turnPage(4,'u_act_p1')"></a></li>
                                    <li id="pg_u_act_p2"><a href="#" id="u_act_p2" onclick="turnPage(4,'u_act_p2')"></a></li>
                                    <li id="pg_u_act_p3"><a href="#" id="u_act_p3" onclick="turnPage(4,'u_act_p3')"></a></li>
                                    <li id="pg_u_act_p4"><a href="#" id="u_act_p4" onclick="turnPage(4,'u_act_p4')"></a></li>
                                    <li id="pg_u_act_p5"><a href="#" id="u_act_p5" onclick="turnPage(4,'u_act_p5')"></a></li>
                                    <li>
                                        <a href="#" aria-label="Next" onclick="next(4)">&raquo;
                                            <%--<span aria-hidden="true">&raquo;</span>--%>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>

            <!--<div class="op-content">-->
            <!--<table class="table table-striped">-->
            <!--<tbody>-->
            <!--<tr>-->
            <!--<td><span class="label label-warning">置顶</span></td>-->
            <!--<td>-->
            <!--<p><span class="event-title">计算机系统结构需要</span></p>-->
            <!--</td>-->
            <!--<td>张慧慧</td>-->
            <!--<td>2017-08-01</td>-->
            <!--</tr>-->
            <!--<tr>-->
            <!--<td><span class="label label-warning">置顶</span></td>-->
            <!--<td>-->
            <!--<p><span class="event-title">计算机系统结构需要</span></p>-->
            <!--</td>-->
            <!--<td>张慧慧</td>-->
            <!--<td>2017-08-01</td>-->
            <!--</tr>-->
            <!--</tbody>-->
            <!--</table>-->
            <!--<nav aria-label="Page navigation">-->
            <!--<ul class="pagination">-->
            <!--<li>-->
            <!--<a href="#" aria-label="Previous">-->
            <!--<span aria-hidden="true">&laquo;</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--<li class="active"><a href="#">1</a></li>-->
            <!--<li><a href="#">2</a></li>-->
            <!--<li><a href="#">3</a></li>-->
            <!--<li><a href="#">4</a></li>-->
            <!--<li><a href="#">5</a></li>-->
            <!--<li>-->
            <!--<a href="#" aria-label="Next">-->
            <!--<span aria-hidden="true">&raquo;</span>-->
            <!--</a>-->
            <!--</li>-->
            <!--</ul>-->
            <!--</nav>-->
            <!--</div>-->
        </div>
    </div>
</div>



<script>
    //此函数初始化页面中的所有分页符
    function pageInit() {
        document.getElementById("pg_t_info_p1").className = "active";
        document.getElementById("pg_t_act_p1").className = "active";
        document.getElementById("pg_u_info_p1").className = "active";
        document.getElementById("pg_u_act_p1").className = "active";
        autoPage("t_info_p1","t_info_p2","t_info_p3","t_info_p4","t_info_p5",${p_c_t_info.totalPage},${p_c_t_info.currentPage});
        autoPage("t_act_p1","t_act_p2","t_act_p3","t_act_p4","t_act_p5",${p_c_t_act.totalPage},${p_c_t_act.currentPage});
        autoPage("u_info_p1","u_info_p2","u_info_p3","u_info_p4","u_info_p5",${p_c_u_info.totalPage},${p_c_u_info.currentPage});
        autoPage("u_act_p1","u_act_p2","u_act_p3","u_act_p4","u_act_p5",${p_c_u_act.totalPage},${p_c_u_act.currentPage});
    }

    function timeStampString(time){
        var datetime = new Date();
        datetime.setTime(time);
        var year = datetime.getFullYear();
        var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
        var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
        return year + "-" + month + "-" + date;
    }

    pageInit();
</script>


<script>
    function next(aid) {
        var cp = 1;
        var index = null;
        var totalpg;
        switch (aid){
            case 1:
                totalpg = ${p_c_t_info.totalPage};
                if(document.getElementById("pg_t_info_p1").className == "active"){
                    cp = document.getElementById("t_info_p1").innerHTML;
                    index = "t_info_p2";
                }
                if(document.getElementById("pg_t_info_p2").className == "active"){
                    cp = document.getElementById("t_info_p2").innerHTML;
                    index = "t_info_p3";
                }
                if(document.getElementById("pg_t_info_p3").className == "active"){
                    cp = document.getElementById("t_info_p3").innerHTML;
                    index = "t_info_p4";
                }
                if(document.getElementById("pg_t_info_p4").className == "active"){
                    cp = document.getElementById("t_info_p4").innerHTML;
                    index = "t_info_p5";
                }
                if(document.getElementById("pg_t_info_p5").className == "active"){
                    cp = document.getElementById("t_info_p5").innerHTML;
                    index = "t_info_p5";
                }
                break;
            case 2:
                totalpg = ${p_c_t_act.totalPage};
                if(document.getElementById("pg_t_act_p1").className == "active"){
                    cp = document.getElementById("t_act_p1").innerHTML;
                    index = "t_act_p2";
                }
                if(document.getElementById("pg_t_act_p2").className == "active"){
                    cp = document.getElementById("t_act_p2").innerHTML;
                    index = "t_act_p3";
                }
                if(document.getElementById("pg_t_act_p3").className == "active"){
                    cp = document.getElementById("t_act_p3").innerHTML;
                    index = "t_act_p4";
                }
                if(document.getElementById("pg_t_act_p4").className == "active"){
                    cp = document.getElementById("t_act_p4").innerHTML;
                    index = "t_act_p5";
                }
                if(document.getElementById("pg_t_act_p5").className == "active"){
                    cp = document.getElementById("t_act_p5").innerHTML;
                    index = "t_act_p5";
                }
                break;
            case 3:
                totalpg = ${p_c_u_info.totalPage};
                if(document.getElementById("pg_u_info_p1").className == "active"){
                    cp = document.getElementById("u_info_p1").innerHTML;
                    index = "u_info_p2";
                }
                if(document.getElementById("pg_u_info_p2").className == "active"){
                    cp = document.getElementById("u_info_p2").innerHTML;
                    index = "u_info_p3";
                }
                if(document.getElementById("pg_u_info_p3").className == "active"){
                    cp = document.getElementById("u_info_p3").innerHTML;
                    index = "u_info_p4";
                }
                if(document.getElementById("pg_u_info_p4").className == "active"){
                    cp = document.getElementById("u_info_p4").innerHTML;
                    index = "u_info_p5";
                }
                if(document.getElementById("pg_u_info_p5").className == "active"){
                    cp = document.getElementById("u_info_p5").innerHTML;
                    index = "u_info_p5";
                }
                break;
            case 4:
                totalpg = ${p_c_u_act.totalPage};
                if(document.getElementById("pg_u_act_p1").className == "active"){
                    cp = document.getElementById("u_act_p1").innerHTML;
                    index = "u_act_p2";
                }
                if(document.getElementById("pg_u_act_p2").className == "active"){
                    cp = document.getElementById("u_act_p2").innerHTML;
                    index = "u_act_p3";
                }
                if(document.getElementById("pg_u_act_p3").className == "active"){
                    cp = document.getElementById("u_act_p3").innerHTML;
                    index = "u_act_p4";
                }
                if(document.getElementById("pg_u_act_p4").className == "active"){
                    cp = document.getElementById("u_act_p4").innerHTML;
                    index = "u_act_p5";
                }
                if(document.getElementById("pg_u_act_p5").className == "active"){
                    cp = document.getElementById("u_act_p5").innerHTML;
                    index = "u_act_p5";
                }
                break;
        }

        clearClass(aid);
        cp = parseInt("10",cp);
        cp = cp + 1;
        if(cp > totalpg){
            document.getElementById("pg_" + index).className = "active";
            alert("已是最后一页");
        }else {
            document.getElementById("pg_" + index).className = "active";
            $.ajax({
                url:"${path}/pagePro?aid=" + aid + "&index=" + document.getElementById(index).innerHTML,
                type:"POST",
                dataType:"json",
                success:function (data) {
                    switch(aid){
                        case 1:
                            autoPage("t_info_p1","t_info_p2","t_info_p3","t_info_p4","t_info_p5",${p_c_t_info.totalPage},cp);

                            break;
                        case 2:
                            autoPage("t_act_p1","t_act_p2","t_act_p3","t_act_p4","t_act_p5",${p_c_t_act.totalPage},cp);
                            break;
                        case 3:
                            autoPage("u_info_p1","u_info_p2","u_info_p3","u_info_p4","u_info_p5",${p_c_u_info.totalPage},cp);
                            break;
                        case 4:
                            autoPage("u_act_p1","u_act_p2","u_act_p3","u_act_p4","u_act_p5",${p_c_u_act.totalPage},cp);
                            break;
                    }
                    $("#tb" + aid).html("");
                    for (var i = 0; i < data.length;i++){
                        var title = data[i].title;
                        if (data[i].sticky == 1){
                            $("#tb" + aid).append("<tr><td><span class=\"label label-warning\">置顶</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                        }else{
                            if(data[i].level == "紧急"){
                                $("#tb" + aid).append("<tr><td><span class=\"label label-danger\">" + data[i].level + "</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                    + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                            }else{
                                $("#tb" + aid).append("<tr><td><span class=\"label label-info\">" + data[i].level + "</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                    + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                            }

                        }

                    }

                }
            });

        }


    }

    function up(aid) {
        var cp = 1;
        var index = null;
        switch (aid){
            case 1:
                if(document.getElementById("pg_t_info_p1").className == "active"){
                    cp = document.getElementById("t_info_p1").innerHTML;
                    index = "t_info_p1";
                }
                if(document.getElementById("pg_t_info_p2").className == "active"){
                    cp = document.getElementById("t_info_p2").innerHTML;
                    index = "t_info_p1";
                }
                if(document.getElementById("pg_t_info_p3").className == "active"){
                    cp = document.getElementById("t_info_p3").innerHTML;
                    index = "t_info_p2";
                }
                if(document.getElementById("pg_t_info_p4").className == "active"){
                    cp = document.getElementById("t_info_p4").innerHTML;
                    index = "t_info_p3";
                }
                if(document.getElementById("pg_t_info_p5").className == "active"){
                    cp = document.getElementById("t_info_p5").innerHTML;
                    index = "t_info_p4";
                }
                break;
            case 2:
                if(document.getElementById("pg_t_act_p1").className == "active"){
                    cp = document.getElementById("t_act_p1").innerHTML;
                    index = "t_act_p2";
                }
                if(document.getElementById("pg_t_act_p2").className == "active"){
                    cp = document.getElementById("t_act_p2").innerHTML;
                    index = "t_act_p3";
                }
                if(document.getElementById("pg_t_act_p3").className == "active"){
                    cp = document.getElementById("t_act_p3").innerHTML;
                    index = "t_act_p4";
                }
                if(document.getElementById("pg_t_act_p4").className == "active"){
                    cp = document.getElementById("t_act_p4").innerHTML;
                    index = "t_act_p5";
                }
                if(document.getElementById("pg_t_act_p5").className == "active"){
                    cp = document.getElementById("t_act_p5").innerHTML;
                    index = "t_act_p5";
                }
                break;
            case 3:
                if(document.getElementById("pg_u_info_p1").className == "active"){
                    cp = document.getElementById("u_info_p1").innerHTML;
                    index = "u_info_p2";
                }
                if(document.getElementById("pg_u_info_p2").className == "active"){
                    cp = document.getElementById("u_info_p2").innerHTML;
                    index = "u_info_p3";
                }
                if(document.getElementById("pg_u_info_p3").className == "active"){
                    cp = document.getElementById("u_info_p3").innerHTML;
                    index = "u_info_p4";
                }
                if(document.getElementById("pg_u_info_p4").className == "active"){
                    cp = document.getElementById("u_info_p4").innerHTML;
                    index = "u_info_p5";
                }
                if(document.getElementById("pg_u_info_p5").className == "active"){
                    cp = document.getElementById("u_info_p5").innerHTML;
                    index = "u_info_p5";
                }
                break;
            case 4:
                if(document.getElementById("pg_u_act_p1").className == "active"){
                    cp = document.getElementById("u_act_p1").innerHTML;
                    index = "u_act_p2";
                }
                if(document.getElementById("pg_u_act_p2").className == "active"){
                    cp = document.getElementById("u_act_p2").innerHTML;
                    index = "u_act_p3";
                }
                if(document.getElementById("pg_u_act_p3").className == "active"){
                    cp = document.getElementById("u_act_p3").innerHTML;
                    index = "u_act_p4";
                }
                if(document.getElementById("pg_u_act_p4").className == "active"){
                    cp = document.getElementById("u_act_p4").innerHTML;
                    index = "u_act_p5";
                }
                if(document.getElementById("pg_u_act_p5").className == "active"){
                    cp = document.getElementById("u_act_p5").innerHTML;
                    index = "u_act_p5";
                }
                break;
        }

        clearClass(aid);
        cp = parseInt("10",cp);
        cp = cp - 1;
        alert(cp);
        if(cp == NaN){
            document.getElementById("pg_" + index).className = "active";
            alert("已是第一页");
        }else {
            document.getElementById("pg_" + index).className = "active";
            $.ajax({
                url:"${path}/pagePro?aid=" + aid + "&index=" + document.getElementById(index).innerHTML,
                type:"POST",
                dataType:"json",
                success:function (data) {
                    switch(aid){
                        case 1:
                            autoPage("t_info_p1","t_info_p2","t_info_p3","t_info_p4","t_info_p5",${p_c_t_info.totalPage},cp);

                            break;
                        case 2:
                            autoPage("t_act_p1","t_act_p2","t_act_p3","t_act_p4","t_act_p5",${p_c_t_act.totalPage},cp);
                            break;
                        case 3:
                            autoPage("u_info_p1","u_info_p2","u_info_p3","u_info_p4","u_info_p5",${p_c_u_info.totalPage},cp);
                            break;
                        case 4:
                            autoPage("u_act_p1","u_act_p2","u_act_p3","u_act_p4","u_act_p5",${p_c_u_act.totalPage},cp);
                            break;
                    }
                    $("#tb" + aid).html("");
                    for (var i = 0; i < data.length;i++){
                        var title = data[i].title;
                        if (data[i].sticky == 1){
                            $("#tb" + aid).append("<tr><td><span class=\"label label-warning\">置顶</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                        }else{
                            if(data[i].level == "紧急"){
                                $("#tb" + aid).append("<tr><td><span class=\"label label-danger\">" + data[i].level + "</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                    + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                            }else {
                                $("#tb" + aid).append("<tr><td><span class=\"label label-info\">" + data[i].level + "</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                    + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                            }

                        }

                    }

                }
            });
        }
    }


</script>


<script>


    function clearClass(aid) {

        switch (aid){
            case 1:
                document.getElementById("pg_t_info_p1").className = "";
                document.getElementById("pg_t_info_p2").className = "";
                document.getElementById("pg_t_info_p3").className = "";
                document.getElementById("pg_t_info_p4").className = "";
                document.getElementById("pg_t_info_p5").className = "";
                break;
            case 2:
                document.getElementById("pg_t_act_p1").className = "";
                document.getElementById("pg_t_act_p2").className = "";
                document.getElementById("pg_t_act_p3").className = "";
                document.getElementById("pg_t_act_p4").className = "";
                document.getElementById("pg_t_act_p5").className = "";
                break;
            case 3:
                document.getElementById("pg_u_info_p1").className = "";
                document.getElementById("pg_u_info_p2").className = "";
                document.getElementById("pg_u_info_p3").className = "";
                document.getElementById("pg_u_info_p4").className = "";
                document.getElementById("pg_u_info_p5").className = "";
                break;
            case 4:
                document.getElementById("pg_u_act_p1").className = "";
                document.getElementById("pg_u_act_p2").className = "";
                document.getElementById("pg_u_act_p3").className = "";
                document.getElementById("pg_u_act_p4").className = "";
                document.getElementById("pg_u_act_p5").className = "";
                break;
        }
    }
    function turnPage(aid , index) {
        var cp = document.getElementById(index).innerHTML;
        clearClass(aid);
        document.getElementById("pg_" + index).className = "active";
        $.ajax({
            url:"${path}/pagePro?aid=" + aid + "&index=" + document.getElementById(index).innerHTML,
            type:"POST",
            dataType:"json",
            success:function (data) {
                switch(aid){
                    case 1:
                        autoPage("t_info_p1","t_info_p2","t_info_p3","t_info_p4","t_info_p5",${p_c_t_info.totalPage},cp);
                        break;
                    case 2:
                        autoPage("t_act_p1","t_act_p2","t_act_p3","t_act_p4","t_act_p5",${p_c_t_act.totalPage},cp);
                        break;
                    case 3:
                        autoPage("u_info_p1","u_info_p2","u_info_p3","u_info_p4","u_info_p5",${p_c_u_info.totalPage},cp);
                        break;
                    case 4:
                        autoPage("u_act_p1","u_act_p2","u_act_p3","u_act_p4","u_act_p5",${p_c_u_act.totalPage},cp);
                        break;
                }
                $("#tb" + aid).html("");
                for (var i = 0; i < data.length;i++){
                    var title = data[i].title;
                    if (data[i].sticky == 1){
                        $("#tb" + aid).append("<tr><td><span class=\"label label-warning\">置顶</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                            + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                    }else{
                        if(data[i].level == "紧急"){
                            $("#tb" + aid).append("<tr><td><span class=\"label label-danger\">" + data[i].level + "</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                        }else {
                            $("#tb" + aid).append("<tr><td><span class=\"label label-info\">" + data[i].level + "</span></td><td><p><a href=\"showIndex?c_title='+title+'\"><span class=\"event-title\">"
                                + data[i].title + "</span></a></p></td><td>"+ data[i].author +"</td>" + "<td>" + timeStampString(data[i].datestamp)+"</td></tr>");
                        }

                    }

                }

            }
        });
        
    }




</script>





<footer>
    <p class="copyright text-center">
        ©2017 中国科学院 信息工程研究所
    </p>
</footer>
</body>
</html>
