<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/9
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/headandbottom.jsp" %>
<html>
<head>
    <title>电子书分享</title>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 40%;margin-left: 20%">
    <div class="mydiv">
        <h2 style="color:#5bc0de ">电子书</h2>
        <hr>
        <c:forEach items="${requestScope.ebooks}" var="ebook" varStatus="ebookStatus">
            <h3>${ebookStatus.index + 1}:${ebook.bookName}</h3>
            <a class="btn mybtn5" role="button" data-toggle="collapse" href="#collapseExample${ebookStatus.index + 1}"
               aria-expanded="false" aria-controls="collapseExample">
                    ${ebook.bookName}
            </a>
            <div class="collapse" id="collapseExample${ebookStatus.index + 1}" style="margin: 5px">
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
<div style="float: left;width: 40%;height: 100%;">
    <div style="background-color: white; margin-top: 20px; margin-left: 30px;width: 50%;height: 300px;padding: 20px">
        <h4>资源分享</h4>
        <hr class="myhr2">
        <a href="${pageContext.request.contextPath}/share/webShare" class="btn mybtn2">网站分享</a>
        <a href="${pageContext.request.contextPath}/share/ebookShare" class="btn mybtn2">电子书分享</a>
        <button class="btn mybtn2">个人心得</button>
        <button class="btn mybtn2">......</button>
    </div>
    <div style="background-color: white; margin-top: 20px; margin-left: 30px;width: 50%;height: 300px;padding: 20px">
        <h4 style="color: red">说明</h4>
        <hr class="myhr2">
        <h4 style="color: red">所有电子资源均从网络获得。</h4>
    </div>
</div>
</body>
</html>
