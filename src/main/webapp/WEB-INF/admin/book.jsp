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
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
</head>
<body>
<form method="post" action="">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 留言管理</strong></div>
        <div class="padding border-bottom">
            <ul class="search">
                <li>
                    <button type="button" class="button border-green" id="checkall"><span
                        class="icon-check"></span> 全选
                    </button>
                    <button type="submit" class="button border-red"
                            onclick="return DelSelect()"><span
                        class="icon-trash-o"></span> 批量删除
                    </button>
                </li>
            </ul>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th width="120">ID</th>
                <th>文章标题</th>
                <th>评论人姓名</th>
                <th>评论人邮箱</th>
                <th width="25%">评论内容</th>
                <th width="120">留言时间</th>
                <th>操作</th>
            </tr>
            <s:iterator value="commentList" var="comment">
                <tr>
                    <td><input type="checkbox" name="id[]" value="${comment.id}"/>${comment.id}
                    </td>
                    <td>${comment.article.title}</td>
                    <td> ${comment.commentName}</td>
                    <td>${comment.commentEmail}</td>
                    <td>${comment.content}</td>
                    <td>${comment.date}</td>
                    <td>
                        <div class="button-group"><a class="button border-red"
                                                     href="javascript:void(0)"
                                                     onclick="return del(${comment.id},${comment.article.id},${sessionScope.user.id})"><span
                            class="icon-trash-o"></span> 删除</a></div>
                    </td>
                </tr>
            </s:iterator>
            <tr>
                <td colspan="8">
                    <div class="pagelist">
                        <s:if test="currPage!=1">
                            <a href="${pageContext.request.contextPath}/comment_getAllComment.action?currPage=1">首页</a>
                            <a href="${pageContext.request.contextPath}/comment_getAllComment.action?currPage=<s:property value="currPage-1"/>">上一页</a>
                        </s:if>
                        <s:if test="currPage!=totalpage">
                            <a href="${pageContext.request.contextPath}/comment_getAllComment.action?currPage=<s:property value="currPage+1"/>">下一页</a>
                            <a href="${pageContext.request.contextPath}/comment_getAllComment.action?currPage=<s:property value="totalpage"/>">尾页</a>
                        </s:if>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">
    //单个评论删除
    function del(cid, aid, uid) {
        if (confirm("您确定要删除吗?")) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET",
                "/comment_deleteComment.action?id=" + cid + "&article.id=" + aid + "&user.id="
                + uid,
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
            }else {
                ///////////
                var xhr = new XMLHttpRequest();
                xhr.open("GET",
                    "comment_adminDeleteCommentsById.action?ids=" + ids,
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
        }
        else {
            alert("请选择您要删除的内容!");
            return false;
        }
    }

</script>
</body>
</html>