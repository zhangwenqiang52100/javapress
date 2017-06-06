<%@ page import="com.opensymphony.xwork2.ActionContext" %><%--
  Created by IntelliJ IDEA.
  User: Archer
  Date: 2016/12/10
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page isELIgnored="false" %>
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
    <title>网站信息</title>
    <link rel="stylesheet" href="../../css/pintuer.css">
    <link rel="stylesheet" href="../../css/admin.css">
    <script src="../../js/jquery.js"></script>
    <script src="../../js/ajaxfileupload.js"></script>
    <script src="../../js/pintuer.js"></script>
    <script src="../../js/info.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 网站信息</strong></div>
    <div class="body-content">
        <form method="POST" class="form-x" action="admin_updataWebOption.action"
              enctype="multipart/form-data"
              namespace="/">
            <div style="display: none">
                <div class="field">
                    <input type="text" name="user.id" value="${sessionScope.user.id}">
                </div>
            </div>
            <div style="display: none">
                <div class="field">
                    <input type="text" name="id" value="${sessionScope.optionInfo.id}">
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站标题：</label>
                </div>
                <div class="field">
                    <input class="input" name="title"
                           value="${sessionScope.optionInfo.title} "/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>网站LOGO：</label>
                </div>
                <div class="field">

                    <input type="text" id="logoName" name="logoName" class="input tips"
                           style="width:25%; float:left;"
                           value="${sessionScope.optionInfo.logoName}"
                           data-toggle="hover" data-place="right"
                           data-image="${sessionScope.optionInfo.logoPath}"/>
                    <input type="button" class="button bg-blue margin-left"
                           onclick="onOpenFile()" value="+ 浏览上传">
                    <input type="file" id="logo"
                           name="logo" value="${sessionScope.optionInfo.logoPath}" style="display: none">
                   <%-- <button id="bt">上传</button>--%>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站关键字：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="keywords"
                              style="height:80px" placeholder="关键字之间请用“，”作为分割">${sessionScope.optionInfo.keywords}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站描述：</label>
                </div>
                <div class="field">
                    <textarea class="input"
                              name="description">${sessionScope.optionInfo.description}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>联系人：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="name"
                           value="${sessionScope.optionInfo.name}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>手机：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="phone"
                           value="${sessionScope.optionInfo.phone}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>电话：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="tel"
                           value="${sessionScope.optionInfo.tel}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>传真：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="fax"
                           value="${sessionScope.optionInfo.fax}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>QQ：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="qq"
                           value="${sessionScope.optionInfo.qq}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>Email：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="email"
                           value="${sessionScope.optionInfo.email}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>地址：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="address"
                           value="${sessionScope.optionInfo.address}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>底部信息：</label>
                </div>
                <div class="field">
                <textarea name="copyright" class="input"
                          style="height:120px;"
                          placeholder="Copyright &copy; 2017.Company name All rights reserved.">${sessionScope.optionInfo.copyright}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <%--</s:iterator>--%>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <input type="submit" class="button bg-main icon-check-square-o" value="提交">
                </div>
                <div style=”color:red”>
                    <s:fielderror/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>