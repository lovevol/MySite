<%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/6
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%response.sendRedirect("/mySite/user/login");%>
</body>
</html>
<%--<html>
<head>
    <title>主页</title>
    <style>
        body {
            margin: 0;
            text-align: center;
        }
        #clipArea {
            margin: 0 auto;
            height: 400px;
            width: 300px;
        }
        #file,
        #clipBtn {
            margin: 20px;
        }
        #view {
            margin: 0 auto;
            width: 200px;
            height: 200px;
        }
    </style>
</head>
<body>

<div style="margin: 0 auto;width: 400px">
    <ul style="text-align: left;color: red">
        <li>使用步骤:</li>
        <li>1、点击选择文件按钮，选择格式为jpg或png的照片</li>
        <li>2、在预览框中拖动图片，白色正方形内区域即为保留区域（鼠标双击可90°旋转图片，鼠标滚轮滚动可放大缩小图片）</li>
        <li>3、点击截取按钮，可预览截取后的图片，如不满意，可按照步骤继续操作。</li>
    </ul>
    <div id="clipArea"></div>
    <input type="file" id="file">
    <button id="clipBtn">截取</button>
    <div id="view"></div>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-2.1.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/iscroll-zoom.js"></script>
<script src="${pageContext.request.contextPath}/js/hammer.js"></script>
<script src="${pageContext.request.contextPath}/js/lrz.all.bundle.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.photoClip.js"></script>
<script>
    //document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
    var clipArea = new bjj.PhotoClip("#clipArea", {
        size: [200, 200],
        outputSize: [640, 640],
        file: "#file",
        view: "#view",
        ok: "#clipBtn",
        loadStart: function() {
            console.log("照片读取中");
        },
        loadComplete: function() {
            console.log("照片读取完成");
        },
        loadError: function() {
            alert("图片加载失败！");
        },
        clipFinish: function(dataURL) {
            console.log("裁剪完成！");
            $("#image").val(dataURL);
        }
    });
    //clipArea.destroy();
</script>
<form action="${pageContext.request.contextPath}/other/uploadImage" method="post" enctype="multipart/form-data">
    <input type="hidden" id="image" name="image">
    <input type="submit" value="保存">
</form>--%>
</body>
</html>