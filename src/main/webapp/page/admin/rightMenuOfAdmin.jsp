<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/16
  Time: 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div style="width: 30%;float: left">
    <div style="background-color: white; margin-top: 20px; margin-left: 20px;width: 40%;padding: 20px">
        <h4>菜单</h4>
        <hr class="myhr1">
        <a href="${pageContext.request.contextPath}/admin/goAddArticle" class="btn mybtn1">文章分享</a>
        <a href="#" class="btn mybtn1">文章管理</a>
        <a href="${pageContext.request.contextPath}/admin/goAddLabel" class="btn mybtn1">标签管理</a>
        <a href="${pageContext.request.contextPath}/admin/goAddCategory" class="btn mybtn1">类别管理</a>
        <a href="#" class="btn mybtn1">资源分享</a>
    </div>
</div>
</body>
</html>
