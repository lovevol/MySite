<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/10
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>文章</title>
</head>
<body style=" padding-top: 70px;background-color: #e4e4e4;">
<div style="left: 10%;padding: 15px;width:10%;height: 500px;position: fixed;background-color: white">
    <h5>标签</h5>
    <hr class="myhr1">
</div>
<div style="margin-left: 21%;padding: 20px;background-color: white;float: left;width: 50%;">
    <h2 class="myh1">${article.title}</h2>
    <h5 style="text-align: center">日期:${article.date}&nbsp;&nbsp;&nbsp;<span>标签:<a href="#" style="color: #f3726d">${article.label.name}</a></span></h5>
    <br>
    <c:if test="${!empty article.imagePath}">
        <img src="/mySite/image/${article.imagePath}" alt="..." class="img-thumbnail" style="width: 100%">
    </c:if>
    <br>
    <p style="font-size: 20px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${article.sketch}</p>
    <hr>
    <div style="width: 100%;overflow: auto">
        ${article.content.content}
    </div>
</div>
<div style="left: 72%;padding: 20px;background-color: white;float: left;height: 300px;position: fixed;width: 15%">
    <h4>文章分类</h4>
    <hr class="myhr1">
    <a href="#" class="btn mybtn1">JAVA语言</a>
    <button class="btn mybtn1">JavaWeb技术</button>
    <button class="btn mybtn1">开发工具</button>
    <button class="btn mybtn1">文学读书笔记</button>
</div>
</body>
</html>
