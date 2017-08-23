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
    <link rel="Shortcut Icon" href="<%=path%>/graph/images/logo.jpg" type="image/x-icon">
    <title>信息录入系统</title>
    <!--单独CSS-->
    <script src="<%=path%>/resources/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="<%=path%>/resources/layer/layer.js" type="text/javascript"></script>
    <!--单独JS-->
    <script type="text/javascript" src="<%=path%>/graph/index.js"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/graph/css/common.css">
    <link href="<%=path%>/graph/css/all.css" rel="stylesheet" type="text/css">
    <script>
        var browserVersion = window.navigator.userAgent.toUpperCase();
        var isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
        var isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
        var isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
        var isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
        var isIE = (!!window.ActiveXObject || "ActiveXObject" in window);
        var isIE9More = (! -[1, ] == false);
        //iframe高度自适应
        function reinitIframe(){
            eval("window.IE9MoreRealHeightwebContent=0");
            var iframe = document.getElementById("webContent");
            var bHeight = 0;
            if (isChrome == false && isSafari == false)
                bHeight = iframe.contentWindow.document.body.scrollHeight;

            var dHeight = 0;
            if (isFireFox == true)
                dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
            else if (isIE == false && isOpera == false)
                dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            else if (isIE == true && isIE9More) {//ie9+
                var heightDeviation = bHeight - eval("window.IE9MoreRealHeightwebContent");
                if (heightDeviation == 0) {
                    bHeight += 3;
                } else if (heightDeviation != 3) {
                    eval("window.IE9MoreRealHeightwebContent=" + bHeight);
                    bHeight += 3;
                }
            }
            else//ie[6-8]、OPERA
                bHeight += 3;

            var height = Math.max(bHeight, dHeight);
            var minHeight = document.body.clientHeight - 60;
            if (height < minHeight) height = minHeight;
            iframe.style.height = height + "px";
            console.log("ifrmae height: "+ height);

            $(function() {
                leftDivScroll();

            });
        }

    </script>

</head>


<!-- ===============导航开始================= -->
<div class="nav">
    <!-- 左面 -->
    <div class="dleft" style="width:369px;">
        <%-- 			<img class="log fl ml15" src="<%=path%>/graph/ysp/main/images/logo.png"> --%>
        <ul class="bt">
            <li class="name ml10">信息录入系统</li>
            <li class="english ml10"> File KnowledgeGraph </li>
        </ul>
    </div>
    <!-- 中间 -->
    <div class="dcenter">
        <div id="navbar" class="center1">
            <div class="center2 center2-selected" id="page1" onclick="switchto(1,'Knowledge/tocorrelation.do');" >界面1</div>
            <div  class="center2" id="page2" onclick="switchto(2,'Knowledge/toextraction.do');" >界面2</div>
           <%--<div class="center2" id="page3" onclick="switchto(3,'artificialSuspicious/tojcdiscover205.do?ctype=5');" >待续</div>--%>

    </div>
</div>

</div>
<!-- ===============下部开始================= -->

<iframe id="webContent" name="webContent"  frameborder="0" scrolling="no" width="100%" height="900px" onload="reinitIframe()" src="Knowledge/tocorrelation.do"></iframe>

</body>

</html>

