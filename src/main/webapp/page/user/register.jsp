<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/13
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/inc.jsp"%>
<html>
<head>
    <title>注册</title>
    <script>
        function checkLoginName() {
            $("#registerForm").ajaxSubmit({
                url:"${pageContext.request.contextPath}/user/validateLoginName",
                async:true,
                type:"post",
                success:function (data) {
                    if (data == "false"){
                        $("#msgForLoginName").css("color","red").text("登录名已存在，请重新填写");
                        $("#loginName").focus();
                        $("#submitBtn").attr("disabled",true);
                    }else {
                        $("#msgForLoginName").css("color","blue").text("用户名可用!");
                        $("#submitBtn").attr("disabled",false);
                    }
                },
                error:function () {
                    alert("验证登录名出错！")
                }

            });
        }

    </script>
</head>
<body background="${pageContext.request.contextPath}/image/back.jpg">
<div style="width: 900px;height:700px;background-color: white;margin: 5% auto;border: solid 1px white" >
    <br>
    <div style="margin:0 auto;width: 800px;height:700px;border: 1px" class="form-group">
        <form action="${pageContext.request.contextPath}/user/validate" method="post" id="registerForm" onsubmit="">
            <h3>注册</h3>
            <table class="table table-hover" style="border: 0;vertical-align: middle;">
                <tr>
                    <td width="100px" style="vertical-align: middle"><label for="userName">用户名:</label></td>
                    <td width="300px"><input type="text" name="userName" id="userName" class="form-control" maxlength="20" placeholder="userName"></td>
                    <td width="300px" style="vertical-align: middle"></td>
                </tr>
                <tr>
                    <td style="vertical-align: middle"> <label for="loginName">登录名:</label></td>
                    <td><input name="loginName" id="loginName" class="form-control" placeholder="loginName" maxlength="20" onchange="checkLoginName()"></td>
                    <td style="vertical-align: middle"> <label id="msgForLoginName">*用于登陆操作</label></td>
                </tr>
                <tr>
                    <td style="vertical-align: middle"><label for="password">密码:</label></td>
                    <td> <input type="password" id="password" name="password" class="form-control" placeholder="pasword" maxlength="20"></td>
                    <td style="vertical-align: middle"></td>
                </tr>
                <tr>
                    <td style="vertical-align: middle"> <label for="password1">重复密码:</label></td>
                    <td><input type="password" id="password1" name="password1" class="form-control" placeholder="pasword" maxlength="20"></td>
                    <td style="vertical-align: middle"></td>
                </tr>
                <tr>
                    <td colspan="3" style="vertical-align: middle">
                        <div style="text-align: center;">
                            <button type="submit" id="submitBtn" class="btn btn-primary">注册</button>
                            <button type="reset" class="btn bg-danger">重置</button>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
