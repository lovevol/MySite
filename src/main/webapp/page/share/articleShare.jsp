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
            var select = $("#label");
            if ($("#category").val() == 0 || $("#category").val() == null) {
                select.empty();
                select.append("<option value='0'>" + "请选择" + "</option>");
                return;
            }
            var idLabel = ${articleVO.label.idLabel}
            $.ajax({
                url:"${pageContext.request.contextPath}/getLabelForAjax?idCategory="+$("#category").val(),
                type:"post",
                contentType:"application/json",
                success:function (data) {
                    /* alert(data);*/

                    select.empty();
                    var data1 = eval('(' + data + ')');
                    if(data1 !== null && data1.length >= 1){
                        select.append("<option value='0'>"+"请选择"+"</option>");
                        for(var i = 0; i < data1.length; i++){
                            if(idLabel == data1[i].idLabel){
                                select.append("<option value="+data1[i].idLabel+" "+"selected"+" "+">"+data1[i].name+"</option>");
                            }
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

        //设置日期选择控件
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
        //提交检查
        function checkInputForSubmit() {
            var startDate = $("#startDate").val();
            if(startDate == "" || startDate == null){
               /* alert("输入开始时间");
                return false;*/
                $("#startDate").val("2000-01-01");
            }
            var endDate = $("#endDate").val();
            if(endDate == "" || endDate == null){
                /*alert("输入结束时间");
                return false;*/
                $("#endDate").val("2999-01-01");
            }
            return true;
        }
        function nextPage() {
            var page =  parseInt($("#page").val());
            var totalPage = parseInt(${articleVO.totalPage});
            if((page + 1) <= totalPage){
                $("#page").val(page + 1);
                $("#searchForm").submit();
            }else {
                alert("已经是尾页");
            }

        }
        function prePage() {
            var page =  $("#page").val();
            if(page - 1 >= 1){
                $("#page").val(page - 1);
                $("#searchForm").submit();
            }else {
                alert("已经是首页");
            }
        }
        function goPage(page) {
            if(page <1 || page > parseInt(${articleVO.totalPage})){
                alert("页码不正确");
                return;
            }
            $("#page").val(page);
            $("#searchForm").submit();
        }
        function setPageSize(size) {
            $("#page").val(1);
            $("#pageSize").val(size);
            $("#searchForm").submit();
        }
        function goFirstPage() {
            $("#page").val(1);
            $("#searchForm").submit();
        }
        function goLastPage() {
            $("#page").val(${articleVO.totalPage});
            $("#searchForm").submit();
        }
        function goSomePage() {
            var page = $("#pageGo").val();
            goPage(page);
        }
        function reset() {
            $("#labelName").val(null);
        }
        function changeLabel() {
            var text =$("#label").find("option:selected").text();
            $("#labelName").val(text);
        }
    </script>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 15%;margin-left: 8%;">
    <div style="padding: 20px" class="mydiv">
        <h4>文章搜索</h4>
        <hr>
        <div class="form-group">
            <form action="${pageContext.request.contextPath}/share/searchArticle" method="post" onsubmit=" return checkInputForSubmit()" id="searchForm">
                <input type="hidden" id="pageSize" name="pageSize" value="${articleVO.pageSize}">
                <input type="hidden" id="page" name="page" value="${articleVO.page}">
                <label for="title">标题:</label>
                <input type="text" name="title" id="title" class="form-control" value="${articleVO.title}">
                <label for="startDate">起时:</label>
                <fmt:formatDate value="${articleVO.startDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                <input type="text" name="startDate" id="startDate" class="form-control">
                <label for="endDate">终时:</label>
                <fmt:formatDate value="${articleVO.endDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
                <input type="text" name="endDate" id="endDate" class="form-control">
                <label for="category">类别:</label>
                <select id="category" name="category.idCategory" onchange="getLabel()" class="form-control">
                    <option value=0>请选择</option>
                    <c:forEach items="${categoriesForArticle}" var="category">
                        <option value="${category.idCategory}" <c:if test="${articleVO.category.idCategory eq category.idCategory}">selected</c:if>>${category.name}</option>
                    </c:forEach>
                </select>
                <label for="label">标签:</label>
                <select id="label" name="label.idLabel" class="form-control" onchange="changeLabel()">
                    <option value=0>请选择</option>
                    <c:if test="${articleVO.label != null && articleVO.label.idLabel != null && articleVO.label.idLabel != 0}">
                        <option value="${articleVO.label.idLabel}" selected="selected">${articleVO.label.name}</option>
                    </c:if>
                </select>
                <input type="hidden" name="label.name" id="labelName" value="${articleVO.label.name}">
                <label for="sketch">简述:</label>
                <input type="text" name="sketch" id="sketch" class="form-control" value="${articleVO.sketch}">

                <button type="submit" class="btn btn-primary">查询</button>
                <button type="reset" class="btn btn-danger" onclick="">重置</button>
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
    <div style="padding: 20px" class="mydiv">
        <nav aria-label="Page navigation" style="text-align: center">
            <ul class="pagination">
                <li><a>每页数目:</a></li>
                <li <c:if test="${articleVO.pageSize == 5}">class="active"</c:if>><a onclick="setPageSize(5)">5</a></li>
                <li <c:if test="${articleVO.pageSize == 10}">class="active"</c:if>><a onclick="setPageSize(10)">10</a></li>
                <li <c:if test="${articleVO.pageSize == 20}">class="active"</c:if>><a onclick="setPageSize(20)">20</a></li>
            </ul>
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous" onclick="goFirstPage()">
                        <span aria-hidden="true" title="首页">&laquo;</span>
                    </a>
                </li>
                <li>
                    <a href="#" aria-label="Previous" onclick="prePage()">
                        <span aria-hidden="true" title="上一页"><</span>
                    </a>
                </li>
                <c:if test="${articleVO.page > 3}">
                    <li>
                        <a onclick="goPage(${articleVO.page-3})" >${articleVO.page-3}</a>
                    </li>
                </c:if>
                <c:if test="${articleVO.page > 2}">
                    <li>
                        <a onclick="goPage(${articleVO.page-2})" >${articleVO.page-2}</a>
                    </li>
                </c:if>
                <c:if test="${articleVO.page > 1}">
                    <li>
                        <a onclick="goPage(${articleVO.page-1})" >${articleVO.page-1}</a>
                    </li>
                </c:if>
                <li class="active"><a onclick="goPage(${articleVO.page})" >${articleVO.page}</a></li>
                <c:if test="${articleVO.page + 1 <= articleVO.totalPage}">
                    <li>
                        <a onclick="goPage(${articleVO.page+1})" >${articleVO.page+1}</a>
                    </li>
                </c:if>
                <c:if test="${articleVO.page + 2 <= articleVO.totalPage}">
                    <li>
                        <a onclick="goPage(${articleVO.page+2})" >${articleVO.page+2}</a>
                    </li>
                </c:if>
                <c:if test="${articleVO.page + 3 <= articleVO.totalPage}">
                    <li>
                        <a onclick="goPage(${articleVO.page+3})" >${articleVO.page+3}</a>
                    </li>
                </c:if>
                <li>
                    <a href="#" aria-label="Next" onclick="nextPage()">
                        <span aria-hidden="true" title="下一页">></span>
                    </a>
                </li>
                <li>
                    <a href="#" aria-label="Next" onclick="goLastPage()">
                        <span aria-hidden="true" title="尾页">&raquo;</span>
                    </a>
                </li>
            </ul>
            <ul class="pagination">
                <li><a>跳转到:</a><span><input type="text" name="pageGo" id="pageGo" style="width: 40px;height: 20px" value="${articleVO.page}">/${articleVO.totalPage}</span></li>
                <li><a onclick="goSomePage()">确定</a></li>
            </ul>
        </nav>
    </div>
</div>
<div style="width: 25%;float: left">
    <div style="background-color: white; margin-top: 20px; margin-left: 20px;width: 60%;padding: 20px">
        <h4>文章分类</h4>
        <hr class="myhr1">
        <c:forEach items="${requestScope.categoriesForArticle}" var="categoriesForArticle">
            <a href="${pageContext.request.contextPath}/share/getArticleByCategory?idCategory=${categoriesForArticle.idCategory}" class="btn mybtn1" title="${categoriesForArticle.description}">${categoriesForArticle.name}</a>
        </c:forEach>
    </div>
</div>
</body>
</html>
