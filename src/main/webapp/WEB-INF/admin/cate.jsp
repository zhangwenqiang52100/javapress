<%@ page import="javax.swing.Action" %>
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
    <script src="../../js/cate.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 分类列表</strong></div>
    <div class="padding border-bottom">
        <ul class="search" style="padding-left:10px;">
            <li>
                <div class="border-bottom">
                    <button type="button" class="button border-yellow"><span
                        class="icon-plus-square-o"></span>
                        <span>添加分类</span>
                    </button>
                    <a href="cate_addOrUpdateCate.action"></a>
                </div>
            </li>
            <li>
                <input type="text" placeholder="请输入搜索关键字" id="keywords" class="input"
                       style="width:250px; line-height:17px;display:inline-block"/>
                <a href="" id="search" class="button icon-search"> 搜索</a>
            </li>
        </ul>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th width="5%">ID</th>
            <th width="15%">栏目</th>
            <th width="15%">分类（标题）</th>
            <th width="15%">分类关键字</th>
            <th width="15%">分类描述</th>
            <th width="10%">操作</th>
        </tr>
        <s:iterator value="cateList" var="cate">
            <tr id="catelist">
                <td>${cate.id}</td>
                <td style="display: none">${cate.taxonomy.id}</td>
                <td>${cate.taxonomy.title}</td>
                <td>${cate.title}</td>
                <td>${cate.keywords}</td>
                <td >${cate.cateDesc}</td>
                <td>
                    <div class="button-group"><a class="button border-main"><span
                        class="icon-edit"></span> <span>修改</span></a>
                        <a class="button border-red" href="javascript:void(0)"
                           onclick="return del(${cate.id},${sessionScope.user.id},${cate.taxonomy.id})"><span
                            class="icon-trash-o"></span> 删除</a></div>
                </td>
            </tr>
        </s:iterator>
        <tr>
            <td colspan="8">
                <div class="pagelist"><s:if test="currPage!=1">
                    <a href="${pageContext.request.contextPath}/cate_searchCateList.action?currPage=1">首页</a>
                    <a href="${pageContext.request.contextPath}/cate_searchCateList.action?currPage=<s:property value="currPage-1"/>">上一页</a>
                </s:if>
                    <s:if test="currPage!=totalpage">
                        <a href="${pageContext.request.contextPath}/cate_searchCateList.action?currPage=<s:property value="currPage+1"/>">下一页</a>
                        <a href="${pageContext.request.contextPath}/cate_searchCateList.action?currPage=<s:property value="totalpage"/>">尾页</a>
                    </s:if>
                </div>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    function del(id, uid, tid) {
        if (confirm("您确定要删除吗?")) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET",
                "/cate_deleteCate.action?id=" + id + "&user.id=" + uid + "&taxonomy.id=" + tid,
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
        class="icon-pencil-square-o"></span><span>添加分类</span></strong>
    </div>
    <div class="body-content">
        <form id="saveOrUpdateCate" method="post" class="form-x"
              action="cate_addOrUpdateCate.action" namespace="/">


            <div style="display: none">
                <div class="field">
                    <input type="text" name="id">

                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>上级分类：</label>
                </div>
                <div class="field">
                    <s:select name="taxonomy.id" list="taxonomyList" listKey="id" listValue="title"
                              class="input w50" headerKey="" headerValue="请选择分类"></s:select>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>分类标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="title"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>分类关键字：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="keywords" placeholder="分类关键字请用“，”分割"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>分类描述：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="cateDesc"/>
                </div>
            </div>
            <%--  <div class="form-group">
                  <div class="label">
                      <label>排序：</label>
                  </div>
                  <div class="field">
                      <input type="text" class="input w50" name="sort" value="0"
                             data-validate="number:排序必须为数字"/>
                      <div class="tips"></div>
                  </div>
              </div>--%>
            <div class="form-group" hidden>
                <div class="label">
                    <label>UID：</label>
                </div>
                <div class="field">
                    <input name="user.id" value="${sessionScope.user.id}"></input>
                    <div class="tips"></div>
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
        </form>
    </div>
</div>
</body>
</html>