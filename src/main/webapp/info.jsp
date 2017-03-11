<%@ page import="com.opensymphony.xwork2.ActionContext" %><%--
  Created by IntelliJ IDEA.
  User: Archer
  Date: 2016/12/10
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 网站信息</strong></div>
    <div class="body-content">
        <s:form method="POST" class="form-x" action="admin_updataWebOption.action"
                namespace="/">
<%--
            <input type="hidden" name="sid"   value="${sessionScope.optionInfo.sid}"/>
--%>
            <div class="form-group">
                <div class="label">
                    <label>网站标题：</label>
                </div>
                <div class="field">
                    <input class="input" name="stitle"
                           value="${sessionScope.optionInfo.stitle} "/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站LOGO：</label>
                </div>
                <div class="field">
                    <input type="text" id="url1" name="slogo" class="input tips"
                           style="width:25%; float:left;" value=""
                           data-toggle="hover" data-place="right" data-image=""/>
                    <input type="button" class="button bg-blue margin-left" id="image1"
                           value="+ 浏览上传">
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站域名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="surl" value="%{optionInfo.surl}"/>
                </div>
            </div>
            <div class="form-group" style="display:none">
                <div class="label">
                    <label>副加标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="sentitle" value="%{model.sentitle}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站关键字：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="skeywords"
                              style="height:80px">%{model.skeywords}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站描述：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="sdescription">%{model.sdescription}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>联系人：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_name" value="%{model.s_name}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>手机：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_phone" value="%{model.s_phone}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>电话：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_tel" value="%{model.s_tel}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>传真：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_fax" value="%{model.s_fax}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>QQ：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_qq" value="%{model.s_qq}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>Email：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_email" value="%{model.s_email}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>地址：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_address" value="%{model.s_address}"/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>底部信息：</label>
                </div>
                <div class="field">
                    <textarea name="scopyright" class="input" style="height:120px;">%{model.scopyright}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <input type="submit" class="button bg-main icon-check-square-o" value="提交">
                </div>
                <div style=”color:red”>
                    <s:fielderror />
                </div>
            </div>
        </s:form>
    </div>
</div>
</body>
</html>