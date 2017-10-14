<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/13
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/inc.jsp" %>
<html>
<head>
    <title>注册</title>
    <script>
        function checkLoginName() {
            var loginName = $("#loginName").val();
            if(loginName != null && loginName !=""){
                $("#registerForm").ajaxSubmit({
                    url: "${pageContext.request.contextPath}/user/validateLoginName",
                    async: true,
                    type: "post",
                    success: function (data) {
                        if (data == "false") {
                            $("#helpBlockForLoginName").text("错误:登录名已存在，换一个吧");
                            changeDivClass("loginNameDiv",3);
                            $("#submitBtn").attr("disabled", true);
                        } else {
                            if (loginName.length <= 1 || loginName.length >= 20){
                                $("#helpBlockForLoginName").text("警告:登录名过长或过短");
                                changeDivClass("loginNameDiv",2);
                            }else{
                                $("#helpBlockForLoginName").text("登录名可用");
                                changeDivClass("loginNameDiv",1);
                                $("#submitBtn").attr("disabled", false);
                            }
                        }
                    },
                    error: function () {
                        alert("验证登录名出错！")
                    }

                });
            }else {
                $("#helpBlockForLoginName").text("错误:登录名格式错误!");
                changeDivClass("loginNameDiv",3);
                $("#loginName").focus();
                $("#submitBtn").attr("disabled", true);
            }

        }
        function checkUserName() {
            var userName = $("#userName").val();
            if(userName != null && userName != ""){
                if(userName.length >= 6 || userName <= 1){
                    $("#helpBlockForUserName").text("警告:用户名过长或过短");
                    changeDivClass("userNameDiv",2);
                }else{
                    $("#helpBlockForUserName").text("用户名正常");
                    changeDivClass("userNameDiv",1);
                }
            }else {
                $("#helpBlockForUserName").text("错误:格式有误！");
                changeDivClass("userNameDiv",3);
                $("#userName").focus();
            }
        }
        function checkPassword() {
            var password = $("#password").val();
            if(password != null && password !="") {
                if (password.length <= 6 || password >= 20) {
                    $("#helpBlockForPassword").text("警告:密码过长或过短");
                    changeDivClass("passwordDiv", 2);
                } else {
                    $("#helpBlockForPassword").text("密码可用");
                    changeDivClass("passwordDiv", 1);
                }
            }else {
                $("#helpBlockForPassword").text("错误:密码格式错误");
                changeDivClass("passwordDiv", 3);
                $("#password").focus();
            }

        }
        function checkPassword1() {
            var password = $("#password").val();
            var password1 = $("#password1").val();
            if (password == password1){
                $("#helpBlockForPassword1").text("密码输入正确");
                changeDivClass("password1Div",1);
            }else {
                $("#helpBlockForPassword1").text("错误:密码输入不一致!");
                changeDivClass("password1Div",3);
                $("#password1").focus();
            }

        }
        function checkEmail() {
            var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            var email = $("#email").val();
            if(reg.test(email)){
                $("#helpBlockForEmail").text("邮件输入正确");
                changeDivClass("emailDiv",1);
            }else {
                $("#helpBlockForEmail").text("错误:格式错误!");
                changeDivClass("emailDiv",3);
                $("#email").focus();
            }
        }
        /**
         * 改变DIV的class
         * @param option 1.success 2.waring 3.error
         */
        function changeDivClass(idDiv,option) {
            var div = $("#"+idDiv);
            if(option == 1){
                div.attr("class","form-group has-success");
            }else if(option == 2){
                div.attr("class","form-group has-warning");
            }else if(option == 3){
                div.attr("class","form-group has-error");
            }
        }
    </script>
</head>
<body background="${pageContext.request.contextPath}/image/back.jpg">
<div style="width: 700px;height:800px;background-color: white;margin: 5% auto;border: solid 1px white">
    <br>
    <div style="margin:0 auto;width: 600px;height:800px;border: 1px">
        <form action="${pageContext.request.contextPath}/user/register" method="post" id="registerForm" onsubmit="">
            <h3>注册</h3>
            <hr>
            <div class="form-group" id="userNameDiv">
                <label class="control-label" for="userName">用户名:</label>
                <input type="text" name="userName" id="userName" class="form-control" maxlength="30"
                       placeholder="userName" onchange="checkUserName()">
                <span id="helpBlockForUserName" class="help-block">昵称或真实姓名</span>
            </div>
            <div class="form-group" id="loginNameDiv">
                <label for="loginName" class="control-label">登录名:</label>
                <input name="loginName" id="loginName" class="form-control" placeholder="loginName"
                       maxlength="30" onchange="checkLoginName()">
                <span id="helpBlockForLoginName" class="help-block">登录名，不可重复</span>
            </div>
            <div class="form-group" id="passwordDiv">
                <label for="password" class="control-label">密码:</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="pasword"
                           maxlength="30" onchange="checkPassword()">
                <span id="helpBlockForPassword" class="help-block">6-20位登陆密码（加密处理）</span>
            </div>
            <div class="form-group" id="password1Div">
                <label for="password1" class="control-label">重复密码:</label>
                <input type="password" id="password1" name="password1" class="form-control"
                           placeholder="pasword" maxlength="30" onchange="checkPassword1()">
                <span id="helpBlockForPassword1" class="help-block">重复密码</span>
            </div>
            <div class="form-group" id="genderDiv">
                <label for="gender" class="control-label"> 性别:</label>
                <select id="gender" name="genderId" class="form-control" style="width: 80px">
                    <option value=1 >男</option>
                    <option value=2 >女</option>
                </select>
            </div>
            <div class="form-group" id="emailDiv">
                <label for="email" class="control-label">Email:</label>
                <input type="email" id="email" name="email" placeholder="email" class="form-control" maxlength="30" onchange="checkEmail()">
                <span id="helpBlockForEmail" class="help-block">可用来找回密码</span>
            </div>
            <hr>
            <div style="text-align: center;">
                <button type="submit" id="submitBtn" class="btn btn-primary">注册</button>
                    <button type="reset" class="btn bg-danger">重置</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
