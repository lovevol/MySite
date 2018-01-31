<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 18-1-27
  Time: 下午7:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>网站管理</title>
    <script type="text/javascript">
        function deleteWeb(id) {
            if(confirm("确定删除？")){
                $.ajax({
                    url:"${pageContext.request.contextPath}/admin/deleteWebById?idWeb="+id,
                    type:"post",
                    contentType:"application/json",
                    success:function (data) {
                        if(data === "success"){
                            $("#web_"+id).hide();
                            alert("删除成功！");
                        }else {
                            alert("出错了！");
                        }
                    },
                    error:function (date) {
                        alert("出错了！");
                    }
                });
            }

        }
    </script>
</head>
<body>
<div style="height: 100%;float: left;width: 50%;margin-left: 20%;">
    <div style=";padding: 20px" class="mydiv">
        <h2 style="color: #d6c84b">网站管理</h2>
        <hr style="border: solid 1px #d6c84b">
        <c:forEach items="${requestScope.webs}" var="web" varStatus="webStatus">
            <div id="web_${web.idWeb}">
                <h3>${webStatus.index + 1}:${web.label}</h3>
                <a style="width: 80%" class="btn mybtn5" role="button" data-toggle="collapse" href="#collapseWeb${webStatus.index + 1} "
                   aria-expanded="false" aria-controls="collapseWeb${webStatus.index + 1}">
                        ${web.webUrl}
                </a>
                <a class="btn mybtn6" style="width: 7%" role="button"  onclick=""><span class="glyphicon glyphicon-edit"></span></a>
                <a class="btn mybtn6" style="width: 7%" role="button"  onclick="deleteWeb(${web.idWeb})"><span class="glyphicon glyphicon-remove"></span></a>
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
            </div>
        </c:forEach>
    </div>
</div>
</body>
<%@include file="rightMenuOfAdmin.jsp"%>
</html>
