<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 17-9-20
  Time: 下午9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>文章</title>
    <script type="text/javascript">
        /*按照类别，动态更新label*/
        function getLabel() {
            $.ajax({
                url:"${pageContext.request.contextPath}/getLabelForAjax?idCategory="+$("#category").val(),
                type:"post",
                contentType:"application/json",
                success:function (data) {
                    /* alert(data);*/
                    var select = $("#label");
                    select.empty();
                    var data1 = eval('(' + data + ')');
                    if(data1 !== null && data1.length >= 1){
                        select.append("<option value='0'>"+"请选择"+"</option>");
                        for(var i = 0; i < data1.length; i++){
                            select.append("<option value="+data1[i].idLabel+">"+data1[i].name+"</option>");
                        }
                    }else {
                        select.append("<option value='0'>该分类下无标签</option>");
                    }
                },
                error:function () {
                    alert("获取相应标签出错");
                }
            });
        }

        $(function() {
            $( "#startDate" ).datepicker({
                changeMonth: true,
                changeYear: true
            });
            $("#startDate").datepicker("option","dateFormat","yy-mm-dd");
            $("#endDate").datepicker({
                changeMonth: true,
                changeYear: true
            });
            $("#endDate").datepicker("option","dateFormat","yy-mm-dd");
        });
        function checkInputForSubmit() {
            var startDate = $("#startDate").val();
            if(startDate == "" || startDate == null){
                alert("输入开始时间");
                return false;
            }
            var endDate = $("#endDate").val();
            if(endDate == "" || endDate == null){
                alert("输入结束时间");
                return false;
            }
            return true;
        }
    </script>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 15%;margin-left: 8%;">
    <div style="padding: 20px" class="mydiv">
        <h4>文章搜索</h4>
        <hr>
        <div class="form-group">
            <form action="${pageContext.request.contextPath}/share/searchArticle" method="post" onsubmit=" return checkInputForSubmit()">
                <label for="title">标题:</label>
                <input type="text" name="title" id="title" class="form-control">
                <label for="startDate">起时:</label>
                <input type="text" name="startDate" id="startDate" class="form-control" >
                <label for="endDate">终时:</label>
                <input type="text" name="endDate" id="endDate" class="form-control">
                <label for="category">类别:</label>
                <select id="category" name="category.idCategory" onchange="getLabel()" class="form-control">
                    <option value=0>请选择</option>
                    <c:forEach items="${categoriesForArticle}" var="category">
                        <option value="${category.idCategory}">${category.name}</option>
                    </c:forEach>
                </select>
                <label for="label">标签:</label>
                <select id="label" name="label.idLabel" class="form-control" >
                    <option value=0 >请选择</option>
                </select>
                <label for="sketch">简述:</label>
                <input type="text" name="sketch" id="sketch" class="form-control">

                <button type="submit" class="btn btn-primary">查询</button>
                <button type="reset" class="btn btn-danger">重置</button>
            </form>
        </div>

    </div>
</div>
<div style="height: 100%;float: left;width: 50%;margin-left: 20px;">
    <c:if test="${articles.size() ne 0}">
        <c:forEach items="${requestScope.articles}" var="article" varStatus="articleStatus">
            <div style="padding: 20px" class="mydiv">
                <h2 class="myh1">${article.title}</h2>
                <h5 style="text-align: center">日期:${article.date}&nbsp;&nbsp;&nbsp;<span>标签:<a href="#" style="color: #f3726d">${article.label.name}</a></span></h5>
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
    </c:if>
    <c:if test="${articles.size() eq 0}">
        <div style="padding: 20px" class="mydiv">
        <h3>对不起，没找到相关结果</h3>
        </div>
    </c:if>
</div>
<div style="width: 25%;float: left">
    <div style="background-color: white; margin-top: 20px; margin-left: 20px;width: 60%;padding: 20px">
        <h4>文章分类</h4>
        <hr class="myhr1">
        <c:forEach items="${requestScope.categoriesForArticle}" var="categoriesForArticle">
            <a href="#" class="btn mybtn1">${categoriesForArticle.name}</a>
        </c:forEach>
    </div>
</div>
</body>
</html>
