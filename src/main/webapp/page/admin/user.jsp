<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 17-12-29
  Time: 上午11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>user</title>
</head>
<body>
<c:forEach items="${users}" var="user">
    ${user.idUser}<br>
</c:forEach>
</body>
</html>
