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
    <script src="../../js/carousel.js"></script>
</head>
<body>
<div class="panel admin-panel margin-top" id="add">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>
        <span>增加内容</span></strong></div>
    <div class="body-content">
        <s:form method="post" class="form-x" action="carousel_addOrUpdateCarouse"
                enctype="multipart/form-data"
                namespace="/">
            <div class="form-group" style="display: none">
                <div class="label">
                    <label>UId：</label>
                </div>
                <div class="field">
                    <input type="hidden" value="${sessionScope.user.id}" name="user.id"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group" style="display: none">
                <div class="label">
                    <label>Id：</label>
                </div>
                <div class="field">
                    <input type="text" value="${editcarousel.id}" name="id"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${editcarousel.title}" name="title"
                           data-validate="required:请输入标题"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>URL：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="url" value="${editcarousel.url}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>图片：</label>
                </div>
                <div class="field">
                    <input type="text" id="imageName" name="imageName" class="input tips"
                           style="width:25%; float:left;" value="${editcarousel.imageName}"
                           data-toggle="hover"
                           data-place="right" data-image="${editcarousel.imagePath}"/>
                    <div>
                        <input type="button" class="button bg-blue margin-left" id="image1"
                               value="+ 浏览上传" style="float:left;" onclick="onOpenFile()">
                        <input type="file" id="image" value="" name="image" style="display: none">
                    </div>
                    <div class="tipss">图片尺寸：1920*500</div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>描述：</label>
                </div>
                <div class="field">
                    <textarea type="text" class="input" name="note" style="height:120px;"
                              value="${editcarousel.note}"></textarea>
                    <div class="tips"></div>
                </div>
            </div>
          <%--  <div class="form-group">
                <div class="label">
                    <label>排序：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="sort" value="${editcarousel.sort}"
                           data-validate="required:,number:排序必须为数字"/>
                    <div class="tips"></div>
                </div>
            </div>--%>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </s:form>
    </div>
</div>
</body>
</html>
