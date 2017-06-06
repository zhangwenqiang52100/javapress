<%@ page import="com.opensymphony.xwork2.ActionContext" %>
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
    <script src="../../js/article.js"></script>
    <script type="text/javascript" src="../../js/nicEdit.js"></script>
    <script type="text/javascript">
        bkLib.onDomLoaded(function() { /*nicEditors.allTextAreas()*/
            new nicEditor().panelInstance('content');});
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加(修改)内容</strong>
    </div>
    <div class="body-content">
        <form method="post" class="form-x" action="article_saveOrUpdateArticle.action"
              enctype="multipart/form-data"
              namespace="/">
            <div class="form-group" style="display: none">
                <div class="label">
                    <label>UID：</label>
                </div>
                <div class="field">
                    <input type="hidden" name="user.id" value="${sessionScope.user.id}">
                </div>
            </div>

            <div class="form-group" style="display: none">
                <div class="label">
                    <label>ID：</label>
                </div>
                <div class="field">
                    <input type="text" name="id" value="${model.id}">
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" value="${model.title}" name="title"
                           data-validate="required:请输入标题"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>图片：</label>
                </div>
                <div class="field">
                    <input type="text" id="imgName" name="imgName" class="input tips"
                           style="width:25%; float:left;" value="${model.imgName}"
                           data-toggle="hover"
                           data-place="right" data-image="${model.imgPath}"/>
                    <input type="button" class="button bg-blue margin-left" id="image1"
                           onclick="onOpenFile()" value="+ 浏览上传" style="float:left;">
                    <input type="file" id="aimg" name="aimg" style="display: none">
                    <div class="tipss">图片尺寸：500*500</div>
                </div>
            </div>

            <if condition="$iscid eq 1">
                <div class="form-group">
                    <div class="label">
                        <label>分类标题：</label>
                    </div>
                    <div class="field">
                        <s:select name="cate.id" list="cateList" listKey="id" listValue="title"
                                  class="input w50" headerKey="" headerValue="请选择分类"></s:select>
                        <div class="tips"></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="label">
                        <label>其他属性：</label>
                    </div>
                    <div class="field" style="padding-top:8px;">
                        <s:checkbox name="isvouch" label="推荐" value="false"/>
                    </div>
                </div>
            </if>
            <div class="form-group">
                <div class="label">
                    <label>描述：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="note"
                              style=" height:90px;">${model.note}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>内容：</label>
                </div>
                <div class="field">
                    <textarea id="content" name="content" class="input"
                              style="height:450px; border:1px solid #ddd;">${model.content}</textarea>
                    <div class="tips"></div>
                </div>
            </div>

            <div class="clear"></div>

            <div class="form-group">
                <div class="label">
                    <label>内容关键字：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" placeholder="内容关键字请用“，”分割" name="keywords" value="${model.keywords}"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>作者：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="authour" value="${model.authour}"/>
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