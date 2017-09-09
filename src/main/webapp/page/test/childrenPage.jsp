<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/7
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
    <title>子窗口</title>
    <script>
        function showParentData() {
            var parent = window.parent.document;
            var inputValue = parent.getElementById("input").value;
            alert(inputValue);
        }
        function clickParentButton() {
            $("#showAlert",window.parent.document).click();
        }
    </script>
</head>
<body>
<button onclick="showParentData()">显示父窗口的数据</button>
<button onclick="clickParentButton()">点击父窗口按钮</button>
</body>
</html>
