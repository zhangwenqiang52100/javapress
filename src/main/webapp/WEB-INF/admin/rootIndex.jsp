<%--
  Created by IntelliJ IDEA.
  User: Archer
  Date: 2016/12/4
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath =
        request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>"></base>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>
    <link rel="stylesheet" href="../../css/pintuer.css">
    <link rel="stylesheet" href="../../css/admin.css">
    <script src="../../js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="../../images/y.jpg"
                 class="radius-circle rotate-hover" height="50" alt=""/>超级管理中心
        </h1>
    </div>
    <div class="head-l"><a class="button button-little bg-red"
                           href="login_loginout.action"><span
        class="icon-power-off"></span> 退出登录</a></div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>用户管理</h2>
    <ul style="display:block">
        <li><a href="user_getAllUser.action" target="right"><span
            class="icon-caret-right"></span>用户管理</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>留言管理</h2>
    <ul>
        <li><a href="comment_getRootAllComment.action" target="right"><span
            class="icon-caret-right"></span>留言内容</a></li>
    </ul>
</div>
<script type="text/javascript">
    $(function () {
        $(".leftnav h2").click(function () {
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function () {
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });
</script>
<ul class="bread">
    <li><a id="a_leader_txt">用户管理</a></li>
    <li><b>当前语言：</b><span style="color:red;">中文</span></li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="user_getAllUser.action" name="right"
            width="100%"
            height="100%"></iframe>
</div>
</body>
</html>