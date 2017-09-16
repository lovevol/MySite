<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/6
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>主页</title>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 50%;margin-left: 15%;">
    <c:forEach items="${requestScope.articles}" var="article" varStatus="articleStatus">
        <div style="padding: 20px" class="mydiv">
            <h2 class="myh1">${article.title}</h2>
            <h5 style="text-align: center">日期:${article.date}&nbsp;&nbsp;&nbsp;<span>标签:<a href="#" style="color: #f3726d">${article.label}</a></span></h5>
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
    <div style=";padding: 20px" class="mydiv">
        <h2 style="color: #d6c84b">最新网站分享</h2>
        <hr style="border: solid 1px #d6c84b">
        <c:forEach items="${requestScope.webs}" var="web" varStatus="webStatus">
            <h3>${webStatus.index + 1}:${web.label}</h3>
            <a class="btn mybtn5" role="button" data-toggle="collapse" href="#collapseWeb${webStatus.index + 1}"
               aria-expanded="false" aria-controls="collapseWeb${webStatus.index + 1}">
                    ${web.webUrl}
            </a>
            <div class="collapse" id="collapseWeb${webStatus.index + 1}" style="margin: 5px">
                <div class="well">
                        ${web.description}
                    <br>
                    <c:if test="${!empty web.remark}">
                        <hr>
                        <label style="color: red">备注:</label>${web.remark}
                    </c:if>
                    <hr>
                    访问:<a href="${web.webUrl}">${web.webUrl}</a>
                </div>
            </div>
            <br>
            <br>
        </c:forEach>
    </div>
    <div style="padding: 20px" class="mydiv">
        <h2 style="color: #26bcd5">最新电子书分享</h2>
        <hr class="myhr2">
        <c:forEach items="${requestScope.ebooks}" var="ebook" varStatus="ebookStatus">
            <h3>${ebookStatus.index + 1}:${ebook.bookName}</h3>
            <a class="btn mybtn4" role="button" data-toggle="collapse" href="#collapseEbook${ebookStatus.index + 1}"
               aria-expanded="false" aria-controls="collapseEbook${ebookStatus.index + 1}">
                    ${ebook.bookName}
            </a>
            <div class="collapse" id="collapseEbook${ebookStatus.index + 1}" style="margin: 5px">
                <div class="well">
                        ${ebook.description}
                    <br>
                    <hr>
                    下载:<a
                        href="${pageContext.request.contextPath}/share/downloadEbook?fileName=${ebook.bookName}">${ebook.bookName}</a>
                </div>
            </div>
            <br>
            <br>
        </c:forEach>
    </div>
</div>
<div style="width: 30%;float: left">
    <div style="background-color: white; margin-top: 30px; margin-left: 30px;width: 50%;padding: 20px">
        <h4>文章分类</h4>
        <hr class="myhr1">
        <c:forEach items="${requestScope.categoriesForArticle}" var="categoriesForArticle">
            <a href="#" class="btn mybtn1">${categoriesForArticle.name}</a>
        </c:forEach>
    </div>
    <div style="background-color: white; margin-top: 30px; margin-left: 30px;width: 50%;padding: 20px">
        <h4>资源分享</h4>
        <hr class="myhr2">
        <c:forEach items="${requestScope.categoriesForShare}" var="categoriesForShare">
            <a href="${pageContext.request.contextPath}/share/webShare" class="btn mybtn2">${categoriesForShare.name}</a>
        </c:forEach>
    </div>
    <div style="background-color: white; margin-top: 30px; margin-left: 30px;width: 50%;padding: 20px">
        <h4>关于作者</h4>
        <hr class="myhr3">
        <a href="#" class="thumbnail">
            <img src="${pageContext.request.contextPath}/image/hao.jpg" alt="">
        </a>
        <table class="table mytable">
            <tr>
                <td>姓名:</td>
                <td>Tan90°</td>
            </tr>
            <tr>
                <td>爱好:</td>
                <td>IT,读书</td>
            </tr>
            <tr>
                <td>方向:</td>
                <td>JavaWeb</td>
            </tr>
            <tr>
                <td>QQ:</td>
                <td>2570059470</td>
            </tr>
            <tr>
                <td>座右铭:</td>
                <td>人各有路，不必羡慕</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
