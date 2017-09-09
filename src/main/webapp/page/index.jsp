<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: lh
  Date: 2017/9/6
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/headandbottom.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
</head>
<body style=" padding-top: 50px;background-color: #e4e4e4;">
<div style="height: 100%;float: left;width: 40%;margin-left: 20%">
    <div style="padding: 20px" class="mydiv">
        <h3 class="myh1">使用SpringMVC构建Web项目引用静态资源的问题</h3>
        <h5 style="text-align: center">日期:<%=new Date()%> <span>标签:<a href="#" style="color: #f3726d">springmvc</a></span></h5>
        <img src="${pageContext.request.contextPath}/image/1.png" alt="..." class="img-thumbnail">
        <br>
        <p style="font-size: 20px">如果你的配置如上图所示，则Spring拦截了所有请求,在引入静态资源，如js,css等时，会出现引入失败的问题。</p>
        <hr>
        <button class="btn btn-primary">阅读全文</button>
    </div>
    <div style="height: 300px;padding: 20px" class="mydiv">
    </div>
    <div style="height: 300px;padding: 20px" class="mydiv">
    </div>
    <div style="height: 300px;padding: 20px" class="mydiv">
    </div>
</div>
<div style="float: left;width: 40%;height: 100%;float: left">
    <div style="background-color: white; margin-top: 30px; margin-left: 30px;width: 50%;height: 400px;padding: 20px">
        <h4>文章分类</h4>
        <hr class="myhr1">
        <a href="#" class="btn mybtn1">JAVA语言</a>
        <button class="btn mybtn1">JavaWeb技术</button>
        <button class="btn mybtn1">开发工具</button>
        <button class="btn mybtn1">文学读书笔记</button>
        <button class="btn mybtn1">......</button>

    </div>
    <div style="background-color: white; margin-top: 30px; margin-left: 30px;width: 50%;height: 300px;padding: 20px">
        <h4>资源分享</h4>
        <hr class="myhr2">
        <a href="${pageContext.request.contextPath}/share/webShare" class="btn mybtn2">网站分享</a>
        <a href="${pageContext.request.contextPath}/share/ebookShare" class="btn mybtn2">电子书分享</a>
        <button class="btn mybtn2">个人心得</button>
        <button class="btn mybtn2">......</button>
    </div>
    <div style="background-color: white; margin-top: 30px; margin-left: 30px;width: 50%;height: 350px;padding: 20px">
        <h4>关于作者</h4>
        <hr class="myhr3">
        <a href="#" class="thumbnail">
            <img src="${pageContext.request.contextPath}/image/hao.jpg" alt="">
        </a>
        <table class="table mytable">
            <tr>
                <td>姓名:</td>
                <td>Tan90°</td>
            </tr>
            <tr>
                <td>爱好:</td>
                <td>IT,读书</td>
            </tr>
            <tr>
                <td>方向:</td>
                <td>JavaWeb</td>
            </tr>
            <tr>
                <td>QQ:</td>
                <td>2570059470</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
