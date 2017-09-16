<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/15
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>添加标签</title>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 40%;margin-left: 20%;">
    <div class="mydiv" style="padding: 20px;" id="divaForLabel">
        <h3>添加标签</h3>
        <hr>
            <div class="form-group">
                <form action="${pageContext.request.contextPath}/admin/addLabel" method="post">
                    <label for="name">标签:</label>
                    <input type="text" name="name" id="name" class="form-control">
                    <label for="idCategory">类目:</label>
                    <select id="idCategory" name="category.idCategory" class="form-control">
                        <c:if test="${!empty categorys}">
                            <c:forEach items="${categorys}" var="category">
                                <option value="${category.idCategory}">${category.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <label for="description">描述:</label>
                    <textarea rows="5" id="description" name="description" class="form-control"></textarea>
                    <hr>
                    <input type="submit" value="提交" class="btn btn-primary">
                    <input type="reset" value="重置" class="btn btn-danger">
                </form>
        </div>
    </div>
    <div class="mydiv" style="padding: 20px;" id="divaForLabel">
        <h3>标签管理</h3>
        <hr>
        <div class="form-group">
            <form action="${pageContext.request.contextPath}/admin/addLabel" method="post">
                <label for="name">标签:</label>
                <input type="text" name="name" id="name" class="form-control">
                <label for="idCategory">类目:</label>
                <select id="idCategory" name="category.idCategory" class="form-control">
                    <c:if test="${!empty categorys}">
                        <c:forEach items="${categorys}" var="category">
                            <option value="${category.idCategory}">${category.name}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <label for="description">描述:</label>
                <textarea rows="5" id="description" name="description" class="form-control"></textarea>
                <hr>
                <input type="submit" value="提交" class="btn btn-primary">
                <input type="reset" value="重置" class="btn btn-danger">
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="leftMenuOfAdmin.jsp"%>
</html>
