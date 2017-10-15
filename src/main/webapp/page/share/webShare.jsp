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
    <title>网站分享</title>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 40%;margin-left: 20%">
    <div class="mydiv">
        <h2 style="color:#5bc0de ">网站</h2>
        <hr>
        <c:forEach items="${requestScope.webs}" var="web" varStatus="webStatus">
            <h3>${webStatus.index + 1}:${web.label}</h3>
            <a class="btn mybtn5" role="button" data-toggle="collapse" href="#collapseExample${webStatus.index + 1}"
               aria-expanded="false" aria-controls="collapseExample">
                    ${web.webUrl}
            </a>
            <div class="collapse" id="collapseExample${webStatus.index + 1}" style="margin: 5px">
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
</div>
<div style="float: left;width: 40%;height: 100%;">
    <div style="background-color: white; margin-top: 20px; margin-left: 30px;width: 50%;height: 300px;padding: 20px">
        <h4>资源分享</h4>
        <hr class="myhr2">
        <a href="${pageContext.request.contextPath}/share/webShare">
            <button class="btn mybtn2">网站分享</button>
        </a>
        <a href="${pageContext.request.contextPath}/share/ebookShare" class="btn mybtn2">电子书分享</a>
        <button class="btn mybtn2">个人心得</button>
        <button class="btn mybtn2">......</button>
    </div>
    <div style="background-color: white; margin-top: 20px; margin-left: 30px;width: 50%;height: 300px;padding: 20px">
        <h4 style="color: red">说明</h4>
        <hr class="myhr2">
        <h4 style="color: red">网站及网站内的资源，由网站的所有者拥有一切解释权。网站的内容由时间的变迁，导致内容的不符，本人不符一切责任。</h4>
    </div>
</div>
</body>
</html>
