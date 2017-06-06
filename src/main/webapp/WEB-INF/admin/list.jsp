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
    <script src="../../js/list.js"></script>
</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href=""
                                                                               style="float:right; display:none;">添加字段</a>
        </div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li><a class="button border-main icon-plus-square-o"
                       href="article_editArticle.action"> 添加内容</a></li>
                <li>搜索：</li>
                <li>
                    <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input"
                           style="width:250px; line-height:17px;display:inline-block"/>
                    <a href="" id="search" class="button border-main icon-search"> 搜索</a>
                </li>
            </ul>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th width="100" style="text-align:left; padding-left:20px;">ID</th>
                <%--
                                <th width="10%">排序</th>
                --%>
                <th>图片</th>
                <th>名称</th>
                <th>推荐</th>
                <th>关键字</th>
                <th>分类名称</th>
                <th width="10%">更新时间</th>
                <th width="310">操作</th>
            </tr>
            <s:iterator value="articleList" var="article">
                <tr>
                    <td style="text-align:left; padding-left:20px;">
                        <input type="checkbox" name="id[]" value="${article.id}"/>${article.id}
                    </td>
                    <td width="10%"><img src="${article.imgPath}" alt="" width="70" height="50"/>
                    </td>
                    <td>${article.title}</td>
                    <td>
                        <div>
                            <input type="checkbox" name="isvouch" disabled
                                   value="${article.isvouch}">推荐
                        </div>
                    </td>
                    <td>${article.keywords}</td>
                    <td>${article.cate.title}</td>
                    <td>${article.datetime}</td>
                    <td>
                        <div class="button-group"><a class="button border-main"
                                                     href="article_findArticleById.action?id=${article.id}&user.id=${sessionScope.user.id}"><span
                            class="icon-edit"></span> 修改</a>
                            <a class="button border-red" href="javascript:void(0)"
                               onclick="return del(${article.cate.id},${article.id},${sessionScope.user.id})"><span
                                class="icon-trash-o"></span> 删除</a></div>
                    </td>
                </tr>
            </s:iterator>
            <tr>
                <td style="text-align:left; padding:19px 0;padding-left:20px;"><input
                    type="checkbox"
                    id="checkall"/>
                    全选
                </td>
                <td colspan="7" style="text-align:left;padding-left:20px;">
                    <a href="javascript:void(0)"
                       class="button border-red icon-trash-o"
                       style="padding:5px 15px;"
                       onclick="DelSelect()"> 删除</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
            </tr>
            <tr>
                <td colspan="8">
                    <div class="pagelist"><s:if test="currPage!=1">
                        <a href="${pageContext.request.contextPath}/article_getArticleList.action?currPage=1">首页</a>
                        <a href="${pageContext.request.contextPath}/article_getArticleList.action?currPage=<s:property value="currPage-1"/>">上一页</a>
                    </s:if>
                        <s:if test="currPage!=totalpage">
                            <a href="${pageContext.request.contextPath}/article_getArticleList.action?currPage=<s:property value="currPage+1"/>">下一页</a>
                            <a href="${pageContext.request.contextPath}/article_getArticleList.action?currPage=<s:property value="totalpage"/>">尾页</a>
                        </s:if>
                    </div>
                </td>
            </tr>

        </table>
    </div>
</form>
<script type="text/javascript">

    //单个删除
    function del(cid, id, uid) {
        if (confirm("您确定要删除吗?")) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET",
                "/article_deleteArticle.action?id=" + id + "&user.id=" + uid + "&cate.id=" + cid,
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

    //全选
    $("#checkall").click(function () {
        $("input[name='id[]']").each(function () {
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect() {
        var Checkbox = false;
        var ids = new Array();
        $("input[name='id[]']").each(function () {
            if (this.checked == true) {
                Checkbox = true;
                var $this = $(this);
                ids.push($this.val());
            }
        });
        if (Checkbox) {
            var t = confirm("您确认要删除选中的内容吗？");
            if (t == false) {
                return false;
            } else {
                ///////////
                var xhr = new XMLHttpRequest();
                xhr.open("GET",
                    "article_deleteArticlesById.action?ids=" + ids,
                    false);
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
            $("#listform").submit();
        }
        else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }
</script>
</body>
</html>