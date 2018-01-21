<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 18-1-21
  Time: 下午12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>浏览历史</title>
</head>
<body style=" padding-top: 70px;background-color: #e4e4e4;">
<div class="mydiv" style="width: 60%;margin-left: 20%">
    <c:forEach items="${histories}" var="url">
        <a href="${url}">${url}</a><br>
    </c:forEach>
</div>

</body>
</html>
