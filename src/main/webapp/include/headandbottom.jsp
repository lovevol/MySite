<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/9
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="inc.jsp" %>
<%--<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>--%>
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
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default" aria-label="Left Align">搜索
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                </button>
            </form>
            <ul class="nav navbar-nav navbar-right" style="margin-right: 15%">
                <li><a href="${pageContext.request.contextPath}/user/login">登陆</a></li>
                <li><a data-toggle="modal" data-target=".bs-example-modal-lg"><span class="glyphicon glyphicon-user"
                                                                                    title="管理员"></span></a></li>
                <li><a href="${pageContext.request.contextPath}/page/admin/addArticle.jsp"><span class="glyphicon glyphicon-plus"></span> </a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">帮助
                        <span class="glyphicon glyphicon-question-sign"></span>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">网站帮助</a></li>
                        <li><a href="#">网站介绍</a></li>
                        <li><a href="#">网站源码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">关于作者</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">管理员身份验证</h4>
            </div>
            <form action="${pageContext.request.contextPath}/verifyAdmin" method="post">
                <div class="modal-body">
                    <div class="form-group">

                        <label for="loginName">登录名:</label><input type="text" id="loginName" name="loginName"
                                                                  class="form-control">
                        <label for="password">密码:</label><input id="password" name="password" type="password"
                                                                class="form-control">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="submit" value="验证" class="btn btn-primary">
                    <input type="reset" value="重置" class="btn btn-danger">
                </div>
            </form>
        </div>
    </div>
</div>
<div>
    <input hidden="hidden" type="text" value="${requestScope.errorMsg}" id="errorMsg">
</div>
</body>
</html>
