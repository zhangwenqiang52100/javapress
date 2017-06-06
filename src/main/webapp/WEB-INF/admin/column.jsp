<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" %>
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
    <title>网站信息</title>
    <link rel="stylesheet" href="../../css/pintuer.css">
    <link rel="stylesheet" href="../../css/admin.css">
    <script src="../../js/jquery.js"></script>
    <script src="../../js/pintuer.js"></script>
    <script src="../../js/taxonomy.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 栏目列表</strong></div>
    <div class="padding border-bottom">
        <a class="button border-yellow" onclick="window.location.hash='#add'"><span
            class="icon-plus-square-o"></span> 添加栏目</a>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">ID</th>
            <th>栏目名称</th>
            <th>栏目描述</th>
            <th>显示（1-显示 0-不显示）</th>
            <th width="250">操作</th>
        </tr>
        <s:iterator value="taxonomyList" var="taxonomy">
            <tr>
                <td>${taxonomy.id}</td>
                <td>${taxonomy.title}</td>
                <td>${taxonomy.taxonomyDesc}</td>
                <td>${taxonomy.isshow}</td>
                <td>
                    <div class="button-group">
                        <a type="button" class="button border-main"><span
                            class="icon-edit"></span><span>修改</span></a>
                        <a class="button border-red" href="javascript:void(0)"
                           onclick="return del(${taxonomy.id},${sessionScope.user.id})"><span
                            class="icon-trash-o"></span> <span>删除</span></a>
                    </div>
                </td>
            </tr>
        </s:iterator>

    </table>
</div>
<script>
    function del(id,uid) {
        if (confirm("删除栏目将删除栏目下的所有分类以及所有文章，您确定要删除吗?")) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "/taxonomy_deleteTaxonomy.action?id=" + id + "&user.id=" + uid,
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
<div class="panel admin-panel margin-top" style="display: none">
    <div class="panel-head" id="add"><strong><span
        class="icon-pencil-square-o"></span><span>增加栏目</span></strong>
    </div>
    <div class="body-content">
        <s:form method="post" id="column" class="form-x" action="taxonomy_addOrUpdate.action"
                namespace="/">
            <div class="form-group" style="display: none">
                <div class="label">
                    <label>UID：</label>
                </div>
                <div class="field">
                    <input type="hidden" name="user.id" value="${sessionScope.user.id}"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group" style="display: none">
                <div class="label">
                    <label>ID：</label>
                </div>
                <div class="field">
                    <input type="hidden" name="id" value=""/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>栏目名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="title" value=""
                           data-validate="required:请输入标题"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>栏目描述：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="taxonomyDesc" value=""/>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="label">
                    <label>显示：</label>
                </div>
                <div class="field">
                    <div class="button-group radio">

                        <label class="button active">
                            <span class="icon icon-check"></span>
                            <input name="isshow" value="1" type="radio" checked="checked">是
                        </label>

                        <label class="button active"><span class="icon icon-times"></span>
                            <input name="isshow" value="0" type="radio" checked="checked">否
                        </label>
                    </div>
                </div>
            </div>
          <%--  <div class="form-group">
                <div class="label">
                    <label>排序：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="sort" value="0"
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