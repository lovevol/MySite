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
    <table style="text-align: center">
        <c:forEach items="${labels}" var="label">
            <tr>
                <td>
                    <a href="#">${label.name}<c:if test="${label.articleNum  ne 0}"></a>
                </td>
                <td style="padding-left: 10px">
                    <span class="badge">${label.articleNum}</span></c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
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
<div style="left: 72%;padding: 20px;background-color: white;float: left;position: fixed;width: 15%">
    <h4>文章分类</h4>
    <hr class="myhr1">
    <c:forEach items="${requestScope.categoriesForArticle}" var="categoriesForArticle">
        <a href="#" class="btn mybtn1">${categoriesForArticle.name}</a>
    </c:forEach>
</div>
</body>
</html>
