<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2018/2/16
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/inc.jsp"%>
<html>
<head>
    <title>用户激活</title>
</head>
<body background="${pageContext.request.contextPath}/image/back.jpg">
<div style="width: 500px;height:440px;background-color: white;margin: 10% auto;border: solid 1px white" >
    <div style="margin:0 auto;width: 400px;height:350px;border: 1px;background-color: white" class="form-group">
        <h3 >激活账号</h3>
        <hr>
        <form method="post" action="${pageContext.request.contextPath}/user/activeUser">
            <label for="loginName">登录名:</label><input id="loginName" name="loginName" value="${loginName}" class="form-control" readonly="readonly">
            <label for="validateCode">验证码:(已通过邮件发送)</label><input id="validateCode" name="validateCode" class="form-control">
            <input type="submit" class="btn btn-primary">
        </form>
    </div>
</div>

</body>
</html>
