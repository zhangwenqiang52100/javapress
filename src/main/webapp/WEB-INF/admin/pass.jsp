<%@ page import="org.hibernate.Session" %>
<%@ page import="com.zwq.domain.User" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
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
    <title></title>
    <link rel="stylesheet" href="../../css/pintuer.css">
    <link rel="stylesheet" href="../../css/admin.css">
    <script src="../../js/jquery.js"></script>
    <script src="../../js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-key"></span> 修改会员密码</strong></div>
    <div class="body-content">
        <s:form method="post" class="form-x" action="user_updatePassword.action" namespace="/">
            <div class="form-group">
                <div class="label">
                    <label for="sitename">管理员帐号：</label>
                </div>
                <div class="field">
                    <label style="line-height:33px;">
                           ${sessionScope.user.userName}
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">原始密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" id="mpass" name="mpass" size="50"
                           placeholder="请输入原始密码" data-validate="required:请输入原始密码"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="newpass" size="50"
                           placeholder="请输入新密码"
                           data-validate="required:请输入新密码,length#>=5:新密码不能小于5位"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label for="sitename">确认新密码：</label>
                </div>
                <div class="field">
                    <input type="password" class="input w50" name="renewpass" size="50"
                           placeholder="请再次输入新密码"
                           data-validate="required:请再次输入新密码,repeat#newpass:两次输入的密码不一致"/>
                </div>
            </div>

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