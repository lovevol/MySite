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
            $("#idLabelForDelete").val($("#idLabel"+id).val());
        }
    </script>
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
                        <c:if test="${!empty categories}">
                            <c:forEach items="${categories}" var="category">
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
    <div class="mydiv" style="padding: 20px;" >
        <h3>标签管理</h3>
        <c:forEach items="${labels}" var="label" varStatus="labelStatus">
            <div style="display: inline">
                <input type="text" id="idLabel${labelStatus.index + 1}" value="${label.idLabel}" hidden="hidden">
                <input type="text" id="name${labelStatus.index + 1}" value="${label.name}" hidden="hidden">
                <input type="text" id="idCategory${labelStatus.index + 1}" value="${label.category.idCategory}" hidden="hidden">
                <input type="text" id="description${labelStatus.index + 1}" value="${label.description}" hidden="hidden">
                <a class="btn mybtn6" style="width: 80%" role="button" data-toggle="collapse" href="#collapseExample${labelStatus.index + 1}"
                   aria-expanded="false" aria-controls="collapseExample">
                        ${labelStatus.index + 1}、${label.name}

                </a>
                <a class="btn mybtn6" style="width: 7%" role="button" data-toggle="modal" data-target="#myModal" onclick="modify('${labelStatus.index + 1}')"><span class="glyphicon glyphicon-edit"></span></a>
                <a class="btn mybtn6" style="width: 7%" role="button" data-toggle="modal" data-target="#myModalForDelete" onclick="deleteCategory('${labelStatus.index + 1}')"><span class="glyphicon glyphicon-remove"></span></a>
            </div>

            <div class="collapse" id="collapseExample${labelStatus.index + 1}" style="margin: 5px">
                <div class="well">
                        ${label.description}
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
                    <label for="nameForModify">标签：</label>
                    <input name="name" id="nameForModify" class="form-control">
                    <label for="typeForModify">类目:</label>
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
                <h4 class="modal-title" id="myModalLabelForDelete">删除标签</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/deleteLabelById" method="post">
                <div class="modal-body">
                    <input name="idLabel" id="idLabelForDelete" hidden="hidden">
                    <h3>确定删除该标签？</h3>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-default" value="确定"/>
                    <input type="reset" class="btn btn-primary" data-dismiss="modal" aria-label="Close" value="取消"/>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
<%@include file="rightMenuOfAdmin.jsp"%>
</html>
