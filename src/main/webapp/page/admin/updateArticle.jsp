<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/10
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/headandbottom.jsp" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/utf8-jsp/ueditor.all.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/utf8-jsp/ueditor.parse.js"></script>
    <title>文章修改</title>
    <script>
        function submitForm() {
            $("#content").val(UE.getEditor('editor').getContent());
            $("#formForArticle").submit();
        }
        /**
         * 按照类别，动态更新label
         */
        function getLabel() {
            $.ajax({
                url:"${pageContext.request.contextPath}/getLabelForAjax?idCategory="+$("#category").val(),
                type:"post",
                contentType:"application/json",
                success:function (data) {
                    var select = $("#label");
                    select.empty();
                    var data1 = eval('(' + data + ')');
                    if(data1 != null && data1.length >= 1){
                        for(var i = 0; i < data1.length; i++){
                            select.append("<option value="+data1[i].idLabel+">"+data1[i].name+"</option>")
                        }
                    }else {

                        select.append("<option value=''>该分类下无标签</option>")
                    }
                },
                error:function () {
                    alert("获取相应标签出错");
                }

            });

        }
    </script>
    <script type="text/javascript">
        $(function () {
            setContent(false);
        });
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var ue = UE.getEditor('editor');


        function isFocus(e) {
            alert(UE.getEditor('editor').isFocus());
            UE.dom.domUtils.preventDefault(e)
        }
        function setblur(e) {
            UE.getEditor('editor').blur();
            UE.dom.domUtils.preventDefault(e)
        }
        function insertHtml() {
            var value = prompt('插入html代码', '');
            UE.getEditor('editor').execCommand('insertHtml', value)
        }
        function createEditor() {
            enableBtn();
            UE.getEditor('editor');
        }
        function getAllHtml() {
            alert(UE.getEditor('editor').getAllHtml())
        }
        function getContent() {
            var arr = [];
            arr.push("使用editor.getContent()方法可以获得编辑器的内容");
            arr.push("内容为：");
            arr.push(UE.getEditor('editor').getContent());
            alert(arr.join("\n"));
        }
        function getPlainTxt() {
            var arr = [];
            arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
            arr.push("内容为：");
            arr.push(UE.getEditor('editor').getPlainTxt());
            alert(arr.join('\n'))
        }
        function setContent(isAppendTo) {
            var arr = [];
            arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
            UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
            alert(arr.join("\n"));
        }
        function setDisabled() {
            UE.getEditor('editor').setDisabled('fullscreen');
            disableBtn("enable");
        }

        function setEnabled() {
            UE.getEditor('editor').setEnabled();
            enableBtn();
        }

        function getText() {
            //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
            var range = UE.getEditor('editor').selection.getRange();
            range.select();
            var txt = UE.getEditor('editor').selection.getText();
            alert(txt)
        }

        function getContentTxt() {
            var arr = [];
            arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
            arr.push("编辑器的纯文本内容为：");
            arr.push(UE.getEditor('editor').getContentTxt());
            alert(arr.join("\n"));
        }
        function hasContent() {
            var arr = [];
            arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
            arr.push("判断结果为：");
            arr.push(UE.getEditor('editor').hasContents());
            alert(arr.join("\n"));
        }
        function setFocus() {
            UE.getEditor('editor').focus();
        }
        function deleteEditor() {
            disableBtn();
            UE.getEditor('editor').destroy();
        }
        function disableBtn(str) {
            var div = document.getElementById('btns');
            var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
            for (var i = 0, btn; btn = btns[i++];) {
                if (btn.id == str) {
                    UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
                } else {
                    btn.setAttribute("disabled", "true");
                }
            }
        }
        function enableBtn() {
            var div = document.getElementById('btns');
            var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
            for (var i = 0, btn; btn = btns[i++];) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            }
        }

        function getLocalData() {
            alert(UE.getEditor('editor').execCommand("getlocaldata"));
        }

        function clearLocalData() {
            UE.getEditor('editor').execCommand("clearlocaldata");
            alert("已清空草稿箱")
        }
    </script>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="margin-left: 10%;margin-top:20px;width: 60%;background-color: white;padding: 20px;float: left;">
    <h2 style="color: #f3726d">文章修改</h2>
    <hr class="myhr1">
    <div class="form-group">
        <form action="${pageContext.request.contextPath}/admin/addArticle" method="post" id="formForArticle"
              enctype="multipart/form-data">
            <label for="title">标题:</label>
            <input type="text" id="title" name="title" class="form-control" value="${article.title}">

            <div style="display: inline">
                <div style="width: 50%;float: left">
                    <label for="category">类别:</label>
                    <select id="category" name="category.idCategory" class="form-control" onchange="getLabel()">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.idCategory}" <c:if test="${category.idCategory eq article.category.idCategory}"> selected</c:if> >${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="width: 50%;float: left">
                    <label for="label">标签:</label>
                    <select id="label" name="label.idLabel" class="form-control">
                        <option value="${article.label.idLabel}">${article.label.name}</option>
                    </select>
                </div>
            </div>

            <label for="image">封面图:</label>
            <input type="file" id="image" name="image" class="form-control" >
            <label for="sketch">简述:</label>
            <textarea rows="4" id="sketch" name="sketch" class="form-control" maxlength="500">${article.sketch}</textarea>
            <input type="text" hidden="hidden" id="content" name="content.content">
        </form>
    </div>
    <h3>正文内容:</h3>
    <!-- 加载编辑器的容器 -->
    <script id="editor" name="editor" type="text/plain" style="width:100%;height:500px;">

    </script>
    <%--<div id="btns">
        <div>
            <button onclick="getAllHtml()">获得整个html的内容</button>
            <button onclick="getContent()">获得内容</button>
            <button onclick="setContent()">写入内容</button>
            <button onclick="setContent(true)">追加内容</button>
            <button onclick="getContentTxt()">获得纯文本</button>
            <button onclick="getPlainTxt()">获得带格式的纯文本</button>
            <button onclick="hasContent()">判断是否有内容</button>
            <button onclick="setFocus()">使编辑器获得焦点</button>
            <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
            <button onmousedown="setblur(event)" >编辑器失去焦点</button>

        </div>
        <div>
            <button onclick="getText()">获得当前选中的文本</button>
            <button onclick="insertHtml()">插入给定的内容</button>
            <button id="enable" onclick="setEnabled()">可以编辑</button>
            <button onclick="setDisabled()">不可编辑</button>
            <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
            <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
            <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
        </div>

        <div>
            <button onclick="getLocalData()" >获取草稿箱内容</button>
            <button onclick="clearLocalData()" >清空草稿箱</button>
        </div>
    </div>--%>
    <button class="btn btn-danger" onclick="submitForm()">文章修改</button>
    </div>
</body>
<%@include file="rightMenuOfAdmin.jsp" %>
</html>