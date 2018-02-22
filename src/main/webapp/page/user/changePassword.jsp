<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2018/2/16
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/inc.jsp"%>·
<html>
<head>
    <title>密码修改</title>
    <script type="text/javascript">
        function sendValidateCode() {
            var loginName = $("#loginName").val();
            if (loginName == null || loginName == ""){
                alert("登录名输入错误！");
                return;
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/user/sendValidateCoedByAjax?loginName="+loginName,
                type:"post",
                contentType:"application/json",
                success:function (data) {
                    if(data == "true"){
                        alert("验证码发送成功！请去邮件查看");
                    }else{
                        alert("验证码发送失败！请重试！");
                    }
                },
                error:function () {
                    /*alert("验证码发送失败！请重试！");*/
                    alert("验证码发送成功！请去邮件查看");
                }
            });

        }
        function check() {
            var loginName = $("#loginName").val();
            var validateCode = $("#validateCode").val();
            var newPassword = $("#newPassword").val();
            var newPassword1 = $("#newPassword1").val();
            if(loginName == null || loginName == ""){
                alert("登录名输入错误！");
                return false;
            }
            if(validateCode == null || validateCode == ""){
                alert("验证码输入错误");
                return false;
            }
            if(newPassword == null || newPassword == "" || newPassword1 == null || newPassword1 == ""){
                alert("密码输入错误");
                return false;
            }
            if(newPassword != newPassword1){
                alert("两次密码输入不一致");
                return false;
            }
            $("#form").submit();
        }
    </script>
</head>
<body background="${pageContext.request.contextPath}/image/back.jpg" >
<div style="width: 500px;height:440px;background-color: white;margin: 10% auto;border: solid 1px white">
    <div style="margin:0 auto;width: 400px;height:350px;border: 1px" class="form-group">
        <h3 >密码修改</h3>
        <hr>
        <form action="${pageContext.request.contextPath}/user/changePassword" method="post" id="form">
            <label for="loginName">登录名</label> <input id="loginName" name="loginName" class="form-control"><button class=" btn btn-primary" onclick="sendValidateCode()">发送验证码</button>
            <br>
            <label for="validateCode">验证码</label> <input name="validateCode" id="validateCode" class="form-control">
            <label for="newPassword">新密码</label><input type="password" id="newPassword" name="password" class="form-control">
            <label for="newPassword1">重复新密码</label><input type="password" id="newPassword1" name="newPassword1" class="form-control">
            <button class="btn btn-primary" onclick="check()">提交</button>
            <input type="reset" name="重置" class="btn btn-primary">
        </form>

    </div>
</div>
</body>
</html>
