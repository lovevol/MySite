<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 18-1-27
  Time: 下午7:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include/headandbottom.jsp"%>
<html>
<head>
    <title>电子书管理</title>
    <script type="text/javascript">
        function deleteEbook(id) {
            if(confirm("确定删除？")){
                $.ajax({
                    url:"${pageContext.request.contextPath}/admin/deleteEbookById?idEbook="+id,
                    type:"post",
                    contentType:"application/json",
                    success:function (data) {
                        if(data === "success"){
                            $("#ebook_"+id).hide();
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
    <div style="padding: 20px" class="mydiv">
        <h2 style="color: #26bcd5">电子书管理</h2>
        <hr class="myhr2">
        <c:forEach items="${requestScope.ebooks}" var="ebook" varStatus="ebookStatus">
            <div id="ebook_${ebook.idEbook}">
                <h3>${ebookStatus.index + 1}:${ebook.bookName}</h3>
                <a style="width: 80%" class="btn mybtn4" role="button" data-toggle="collapse" href="#collapseEbook${ebookStatus.index + 1}"
                   aria-expanded="false" aria-controls="collapseEbook${ebookStatus.index + 1}">
                        ${ebook.bookName}
                </a>
                <a class="btn mybtn6" style="width: 7%" role="button"  onclick=""><span class="glyphicon glyphicon-edit"></span></a>
                <a class="btn mybtn6" style="width: 7%" role="button"  onclick="deleteEbook(${ebook.idEbook})"><span class="glyphicon glyphicon-remove"></span></a>
                <div class="collapse" id="collapseEbook${ebookStatus.index + 1}" style="margin: 5px">
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
            </div>

        </c:forEach>
    </div>
</div>
</body>
<%@include file="rightMenuOfAdmin.jsp"%>
</html>
