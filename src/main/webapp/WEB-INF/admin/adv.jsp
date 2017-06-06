<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath =
        request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <base href="<%=basePath%>"></base>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="../../css/pintuer.css">
    <link rel="stylesheet" href="../../css/admin.css">
    <script src="../../js/jquery.js"></script>
    <script src="../../js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel" id="list">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
    <div class="padding border-bottom">
        <button id="addcontent" type="button" class="button border-yellow"
                onclick="window.location.href='carousel_addCarousel.action'">
            <span class="icon-plus-square-o"></span> 添加内容
        </button>
    </div>
    <table class="table table-hover text-center" id="contenttable">
        <tr>
            <th width="10%">ID</th>
            <th width="15%">名称</th>
            <th width="20%">图片</th>
            <th width="20%">描述</th>
        <%--    <th width="10%">排序</th>--%>
            <th width="15%">操作</th>
        </tr>
        <s:iterator value="carouselList" var="carousel">
            <tr>
                <td>${carousel.id}</td>
                <td>${carousel.title}</td>
                <td style="display: none">${carousel.url}</td>
                <td><img src="<%=basePath%>${carousel.imagePath}" alt="" width="120" height="50"/></td>
                <td>${carousel.note}</td>
               <%-- <td>${carousel.sort}</td>--%>
                <td>
                    <div class="button-group">
                        <a href="carousel_editCarousel.action?id=${carousel.id}" class="button border-main"><span
                            class="icon-edit"></span> 修改</a>
                        <a class="button border-red" href="javascript:void(0)"
                           onclick="return del(${carousel.id},${sessionScope.user.id})"><span
                            class="icon-trash-o"></span>
                            删除</a>
                    </div>
                </td>
            </tr>
        </s:iterator>


    </table>
</div>
<script type="text/javascript">

    function del(id, uid) {
        if (confirm("您确定要删除吗?")) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/carousel_deleteCarousel.action?id=" + id + "&user.id=" + uid,
                true);
            xhr.send(null);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        alert("删除成功，请刷新页面显示最新数据");
                    } else {
                        alert("删除失败");
                    }
                }
            };
        }
    }
</script>
</body>
</html>