<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/6
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/inc.jsp"%>
<html>
<head>

    <script>
    </script>
    <title>登陆</title>
</head>
<body background="${pageContext.request.contextPath}/image/back.jpg">
<div style="width: 500px;height:400px;background-color: white;margin: 10% auto;border: solid 1px white" >
    <br>
    <div style="margin:0 auto;width: 400px;height:350px;border: 1px" class="form-group">
        <form action="${pageContext.request.contextPath}/user/validate" method="post">
            <h3 style="color: ">登陆</h3>
            <span style="float: right">没有账户号?<a href="#">注册</a></span>
            <hr>
            <label for="loginName">登录名:</label>
            <input name="loginName" id="loginName" class="form-control" placeholder="loginName">
            <span class="help-block" id="helpForLoginName">注意:是登陆名而不是用户名</span>
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="pasword">
            <span class="help-block" id="helpForPassword">忘记密码？<a href="#">点我</a></span>
            <div style="text-align: center;width: 100%">
                <input type="radio" name="roleType" id="roleType1" value="1" checked="checked"><label for="roleType1">用户</label>
                <input type="radio" name="roleType" id="roleType2" value="2" style="margin-left: 30px"><label for="roleType2">管理员</label>
            </div>
            <hr>
            <span style="float: right"><a href="${pageContext.request.contextPath}/index">游客访问</a></span>
            <div style="text-align: center;">
                <button type="submit" class="btn btn-primary">提交</button>
                <button type="reset" class="btn bg-danger">重置</button>
            </div>

        </form>
    </div>
</div>
</body>
</html>
