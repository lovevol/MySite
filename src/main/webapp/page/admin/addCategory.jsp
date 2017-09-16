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
    <script>
        function showEditeDialog() {
            dialog();
        }

    </script>
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
        <c:forEach items="${categories}" var="category" varStatus="categoryStatus">
            <div style="display: inline">
                <a class="btn mybtn6" style="width: 80%" role="button" data-toggle="collapse" href="#collapseExample${categoryStatus.index + 1}"
                   aria-expanded="false" aria-controls="collapseExample">
                        ${categoryStatus.index + 1}、${category.name}

                </a>
                <a class="btn mybtn6" style="width: 7%" role="button" onclick="showEditeDialog()"><span class="glyphicon glyphicon-edit"></span></a>
                <a class="btn mybtn6" style="width: 7%" role="button"><span class="glyphicon glyphicon-remove"></span></a>
            </div>

            <div class="collapse" id="collapseExample${categoryStatus.index + 1}" style="margin: 5px">
                <div class="well">
                        ${category.description}
                    <br>
                </div>
            </div>
            <br>
            <br>
        </c:forEach>
    </div>
</div>
<div id="dialog">
    3133333333333
</div>
</body>
<%@include file="rightMenuOfAdmin.jsp"%>
</html>
