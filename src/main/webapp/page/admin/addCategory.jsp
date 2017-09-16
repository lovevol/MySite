<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/15
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>管理分类和标签</title>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 40%;margin-left: 20%;">
    <div class="mydiv">
        <h3>添加类目</h3>
        <hr>
        <div class="form-group">
            <form action="${pageContext.request.contextPath}/admin/addCategory" method="post">
                <label for="name">类目:</label>
                <input type="text" name="name" id="name" class="form-control">
                <label for="type">类型:</label>
                <select id="type" name="type" class="form-control">
                    <option id="type1" value="1">文章分享</option>
                    <option id="type2" value="2">资源分享</option>
                </select>
                <label for="description">描述:</label>
                <textarea rows="5" id="description" name="description" class="form-control"></textarea>
                <hr>
                <input type="submit" value="提交" class="btn btn-primary">
                <input type="reset" value="重置" class="btn btn-danger">
            </form>
        </div>
    </div>
    <div class="mydiv">
        <h3>类目管理</h3>
        <hr>
    </div>
</div>
</body>
<%@include file="leftMenuOfAdmin.jsp"%>
</html>
