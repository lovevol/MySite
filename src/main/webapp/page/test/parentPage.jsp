<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/7
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
    <title>父窗口与子窗口测试</title>
    <script type="text/javascript">
        function showChildrenPage() {
            $("#input").val("a");
            var iframe = document.getElementById("iframe");
            iframe.src="childrenPage.jsp";
            iframe.style.display = "";
        }
        function showAlert() {
            alert("alert");
        }
    </script>
</head>
<body>
<button onclick="showChildrenPage()">点击显示对话框</button>
<button id="showAlert" onclick="showAlert()">弹出框</button>
<label for="input">输入</label><input id="input" type="text">

<iframe src="" style="width: 400px;height: 400px; display: none;z-index: 99;position: absolute;top: 100px;left: 100px;" id="iframe"></iframe>
</body>
</html>
