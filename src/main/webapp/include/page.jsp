<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 18-1-23
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function nextPage() {
        var page =  parseInt($("#page").val());
        var totalPage = parseInt($("#totalPage").val());
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
        if(page <1 || page > parseInt($("#totalPage").val())){
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
        $("#page").val($("#totalPage").val());
        $("#searchForm").submit();
    }
    function goSomePage() {
        var page = $("#pageGo").val();
        goPage(page);
    }
</script>
<div style="padding: 20px" class="mydiv">
    <nav aria-label="Page navigation" style="text-align: center">
        <ul class="pagination">
            <li><a>每页数目:</a></li>
            <li <c:if test="${articleVO.pageSize == 5}">class="active"</c:if>><a href="javascript:setPageSize(5);" >5</a></li>
            <li <c:if test="${articleVO.pageSize == 10}">class="active"</c:if>><a href="javascript:setPageSize(10);">10</a></li>
            <li <c:if test="${articleVO.pageSize == 20}">class="active"</c:if>><a href="javascript:setPageSize(20);">20</a></li>
        </ul>
        <ul class="pagination">
            <li>
                <a aria-label="Previous" href="javascript:goFirstPage();">
                    <span aria-hidden="true" title="首页">&laquo;</span>
                </a>
            </li>
            <li>
                <a aria-label="Previous" href="javascript:prePage();">
                    <span aria-hidden="true" title="上一页"><</span>
                </a>
            </li>
            <c:if test="${articleVO.page > 3}">
                <li>
                    <a href="javascript:goPage(${articleVO.page-3});" >${articleVO.page-3}</a>
                </li>
            </c:if>
            <c:if test="${articleVO.page > 2}">
                <li>
                    <a href="javascript:goPage(${articleVO.page-2});" >${articleVO.page-2}</a>
                </li>
            </c:if>
            <c:if test="${articleVO.page > 1}">
                <li>
                    <a onclick="goPage(${articleVO.page-1})" >${articleVO.page-1}</a>
                </li>
            </c:if>
            <li class="active"><a href="javascript:goPage(${articleVO.page})">${articleVO.page}</a></li>
            <c:if test="${articleVO.page + 1 <= articleVO.totalPage}">
                <li>
                    <a href="javascript:goPage(${articleVO.page+1});" >${articleVO.page+1}</a>
                </li>
            </c:if>
            <c:if test="${articleVO.page + 2 <= articleVO.totalPage}">
                <li>
                    <a href="javascript:goPage(${articleVO.page+2});" >${articleVO.page+2}</a>
                </li>
            </c:if>
            <c:if test="${articleVO.page + 3 <= articleVO.totalPage}">
                <li>
                    <a href="javascript:goPage(${articleVO.page+3});" >${articleVO.page+3}</a>
                </li>
            </c:if>
            <li>
                <a aria-label="Next" href="javascript:nextPage();">
                    <span aria-hidden="true" title="下一页">></span>
                </a>
            </li>
            <li>
                <a aria-label="Next" href="javascript:goLastPage();">
                    <span aria-hidden="true" title="尾页">&raquo;</span>
                </a>
            </li>
        </ul>
        <ul class="pagination">
            <li><a>跳转到:</a><span><input type="text" name="pageGo" id="pageGo" style="width: 40px;height: 20px" value="${articleVO.page}">/${articleVO.totalPage}</span></li>
            <li><a href="javascript:goSomePage();">确定</a></li>
        </ul>
    </nav>
</div>
