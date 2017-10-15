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
        $(function() {
            $( "#dialog" ).dialog({
                autoOpen: false,
                show: {
                    effect: "blind",
                    duration: 1000
                },
                hide: {
                    effect: "explode",
                    duration: 1000
                }
            });

            $( "#opener" ).click(function() {
                $( "#dialog" ).dialog( "open" );
            });
        });
        function modify(id) {
            $("#idCategoryForModify").val($("#idCategory"+id).val());
            $("#nameForModify").val($("#name"+id).val());
            $("#typeForModify").val($("#type"+id).val());
            $("#descriptionForModify").text($("#description"+id).val());
        }
        function deleteCategory(id){
            $("#idCategoryForDelete").val($("#idCategory"+id).val());
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
                <input type="text" id="idCategory${categoryStatus.index + 1}" value="${category.idCategory}" hidden="hidden">
                <input type="text" id="name${categoryStatus.index + 1}" value="${category.name}" hidden="hidden">
                <input type="text" id="type${categoryStatus.index + 1}" value="${category.type}" hidden="hidden">
                <input type="text" id="description${categoryStatus.index + 1}" value="${category.description}" hidden="hidden">
                <a class="btn mybtn6" style="width: 80%" role="button" data-toggle="collapse" href="#collapseExample${categoryStatus.index + 1}"
                   aria-expanded="false" aria-controls="collapseExample">
                        ${categoryStatus.index + 1}、${category.name}

                </a>
                <a class="btn mybtn6" style="width: 7%" role="button" data-toggle="modal" data-target="#myModal" onclick="modify('${categoryStatus.index + 1}')"><span class="glyphicon glyphicon-edit"></span></a>
                <a class="btn mybtn6" style="width: 7%" role="button" data-toggle="modal" data-target="#myModalForDelete" onclick="deleteCategory('${categoryStatus.index + 1}')"><span class="glyphicon glyphicon-remove"></span></a>
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
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改标签</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/modifyCategory" method="post">
                <div class="modal-body">
                    <input name="idCategory" id="idCategoryForModify" hidden="hidden">
                    <label for="nameForModify">类目：</label>
                    <input name="name" id="nameForModify" class="form-control">
                    <label for="typeForModify">类型:</label>
                    <select id="typeForModify" name="type" class="form-control">
                        <option id="type1ForModify" value="1">文章分享</option>
                        <option id="ForModifytype2" value="2">资源分享</option>
                    </select>
                    <label for="descriptionForModify">描述:</label>
                    <textarea rows="5" id="descriptionForModify" name="description" class="form-control"></textarea>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-default" value="保存"/>
                    <input type="reset" class="btn btn-primary" value="重置"/>
                </div>
            </form>

        </div>
    </div>
</div>
<div class="modal fade" id="myModalForDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabelForDelete">删除类别</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/deleteCategoryById" method="post">
                <div class="modal-body">
                    <input name="idCategory" id="idCategoryForDelete" hidden="hidden">
                    <h3>确定删除该类别？</h3>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-default" value="确定"/>
                    <input type="reset" class="btn btn-primary" data-dismiss="modal" aria-label="Close" `value="取消"/>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
<%@include file="rightMenuOfAdmin.jsp"%>
</html>
