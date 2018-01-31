<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/9
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="inc.jsp" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script>
        $(function () {
            var errorMsg = $("#errorMsg").val();
            if(errorMsg != null && errorMsg != ""){
                alert(errorMsg);
            }
        })
    </script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" style="margin-left: 15%">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index">Tan90°</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/index">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search" name="keyWord" id="keyWord">
                </div>
                <button type="submit" class="btn btn-default" aria-label="Left Align">搜索
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                </button>
                <span class="glyphicon glyphicon-question-sign" title="这里输入的关键字可搜索文章标题，电子书的名称，网站的介绍关键字，但是只能展示部分结果，要想搜到更多更准确的内容，请到相关页面进行精确搜索。"></span>
            </form>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 15%">
                <c:choose>
                    <c:when test="${! empty sessionScope.user}">
                        <c:choose>
                            <c:when test="${sessionScope.user.roleType eq 1}">
                                <li><a href="${pageContext.request.contextPath}/user/myInfo">欢迎用户:${sessionScope.user.userName}</a></li>
                                <li><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
                            </c:when>
                            <c:otherwise>
                               <li><a href="${pageContext.request.contextPath}/admin/indexOfAdmin">欢迎管理员:${sessionScope.user.userName}</a> </li>
                                <li><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a href="${pageContext.request.contextPath}/other/history">浏览记录</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/user/login">登陆</a></li>
                    </c:otherwise>
                </c:choose>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">帮助
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">网站帮助</a></li>
                        <li><a href="${pageContext.request.contextPath}/page/other/mySiteInfo.jsp">网站介绍</a></li>
                        <li><a href="http://github.com/lovevol/MySite" target="_blank">网站源码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">关于作者</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div>
    <input hidden="hidden" type="text" value="${requestScope.errorMsg}" id="errorMsg">
</div>
</body>
</html>
