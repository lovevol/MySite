<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 18-1-23
  Time: 下午3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/headandbottom.jsp" %>
<html>
<head>
    <title>我的信息</title>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="margin-left: 21%;float: left;width: 50%;">
    <div style="padding: 20px" class="mydiv">
        <h2>收藏的文章</h2>
        <hr class="myhr1">
    </div>
    <c:forEach items="${articles}" var="article" varStatus="articleStatus">
        <div style="padding: 20px" class="mydiv">
            <h2 class="myh1">${article.title}</h2>
            <h5 style="text-align: center">日期:${article.date}&nbsp;&nbsp;&nbsp;<span>标签:<a href="#" style="color: #f3726d">${article.label.name}</a></span></h5>
            <br>
            <c:if test="${!empty article.imagePath}">
                <img src="/mySite/image/${article.imagePath}" alt="..." class="img-thumbnail" style="width: 100%">
            </c:if>
            <br>
            <p style="font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${article.sketch}</p>
            <hr>
            <form action="${pageContext.request.contextPath}/share/readArticle" method="post">
                <input type="number" hidden="hidden" value="${article.idArticle}" name="articleId" id="articleId">
                <input type="submit"  class="btn btn-primary" value="阅读全文">
            </form>

        </div>
    </c:forEach>
</div>
<div style="left: 72%;padding: 20px;background-color: white;float: left;position: fixed;width: 15%;margin-top: 20px">
    <h4>我的信息</h4>
    <hr class="myhr1">
    <table class="table mytable">
        <tr>
            <td>用户名:</td><td>${user.userName}</td>
        </tr>
        <tr>
            <td>用户ID:</td><td>${user.idUser}</td>
        </tr>
        <tr>
            <td>登陆名:</td><td>${user.loginName}</td>
        </tr>
        <tr>
            <td>E-mail:</td><td>${user.email}</td>
        </tr>
        <tr>
            <td colspan="2"><button class="btn btn-danger">修改信息</button>
            <a href="${pageContext.request.contextPath}/page/user/changePassword.jsp">修改密码</a></td>
        </tr>
    </table>
</div>
</body>
</html>
