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
    <script type="text/javascript">
        function saveArticle(articleId) {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/saveArticleByAjax?articleId="+articleId,
                type:"post",
                contentType:"application/json",
                success:function (data) {
                    /* alert(data);*/
                    if(data === "true"){
                        alert("收藏成功！");
                        changeStyle(1);
                    }else {
                        alert("收藏失败！请登陆后重试！")
                    }
                },
                error:function () {
                    alert("收藏失败！请登陆后重试！")
                }
            });
        }
        function saveComment(articleId) {
            var content = $("#content").val();
            if(content == null || content == ""){
                alert("请输入正确的内容！");
                return;
            }
            $.ajax({
                url:"${pageContext.request.contextPath}/user/saveComment",
                type:"post",
                dataType: 'json',
                data:{"articleId":articleId,"content":content},
                success:function (data) {
                    alert("评论成功，刷新可见");
                },
                error:function () {
                    alert("失败!")
                }
            })
        }
        function cancelArticle(articleId) {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/cancelArticleByAjax?articleId="+articleId,
                type:"post",
                contentType:"application/json",
                success:function (data) {
                    /* alert(data);*/
                    if(data === "true"){
                        alert("取消收藏成功！");
                        changeStyle(2);
                    }else {
                        alert("取消收藏失败！请重试！")
                    }
                },
                error:function () {
                    alert("取消收藏失败！请重试！")
                }
            });
        }
        function changeStyle(type) {
            if (type == 1){
                $("#cancel").show();
                $("#save").hide();
            }else if (type == 2){
                $("#cancel").hide();
                $("#save").show();
            }
        }
        function getArticleByLabelId(id,name) {
            $("#idLabel").val(id);
            $("#labelName").val(name);
            $("#idLabelForm").submit();
        }
    </script>
</head>
<body style=" padding-top: 70px;background-color: #e4e4e4;">
<div style="left: 10%;padding: 15px;width:10%;height: 500px;position: fixed;background-color: white">
    <h5>相关标签</h5>
    <form action="${pageContext.request.contextPath}/share/searchArticle" method="post" id="idLabelForm">
        <input type="hidden" id="idLabel" name="label.idLabel">
        <input type="hidden" id="labelName" name="label.name">
    </form>
    <hr class="myhr1">
    <table class="table mytable">
        <c:forEach items="${labels}" var="label">
            <tr>
                <td>
                    <a href="javascript:getArticleByLabelId('${label.idLabel}','${label.name}');">${label.name}</a>
                </td>
                <td style="padding-left: 10px">
                    <span class="badge">${label.articleNum}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div style="margin-left: 21%;padding: 20px;background-color: white;float: left;width: 50%;">
    <span>
        <span class="glyphicon glyphicon-eye-open" style="float: right;font-size: 20px;color:cadetblue;"><span style="margin-left: 10px">${num}</span></span>
    </span>

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
    <hr>

    <span id="cancel" <c:if test="${saved == null}">style="display: none" </c:if>>
        <a href="javascript:cancelArticle(${article.idArticle});"><span class="glyphicon glyphicon-star" style="float: right;font-size: 25px;color:cadetblue;margin-left: 10px" id="star1"></span></a><span style="float: right">取消收藏:</span>
    </span>
    <span id="save" <c:if test="${saved != null && saved == 'true'}">style="display: none" </c:if>>
        <a href="javascript:saveArticle(${article.idArticle});"><span class="glyphicon glyphicon-star-empty" style="float: right;font-size: 25px;color:cadetblue;margin-left: 10px" id="star2"></span></a><span style="float: right">收藏:</span>
    </span>
</div>
<div style="margin-left: 21%;margin-top:20px;padding: 20px;background-color: white;float: left;width: 50%;">
    <label for="content">写评论</label> <textarea name="content" id="content" class="form-control" maxlength="200"></textarea>
    <button class="btn btn-primary" onclick="saveComment('${article.idArticle}')">提交</button>
</div>
<div style="margin-left: 21%;margin-top:20px;padding: 20px;background-color: white;float: left;width: 50%;">
    <label>评论</label>
    <c:forEach items="${comments}" var="comment" varStatus="index" >
        <hr>
        <c:if test="${comment.userId == -1}">
            火星人:
        </c:if>
        <c:if test="${comment.userId != -1}">
            用户${comment.userId}:
        </c:if>
        ${comment.content}
    </c:forEach>
</div>
<div style="left: 72%;padding: 20px;background-color: white;float: left;position: fixed;width: 15%">
    <h4>文章分类</h4>
    <hr class="myhr1">
    <c:forEach items="${requestScope.categoriesForArticle}" var="categoriesForArticle">
        <a href="${pageContext.request.contextPath}/share/searchArticle?category.idCategory=${categoriesForArticle.idCategory}" class="btn mybtn1">${categoriesForArticle.name}</a>
    </c:forEach>
</div>
</body>
</html>
