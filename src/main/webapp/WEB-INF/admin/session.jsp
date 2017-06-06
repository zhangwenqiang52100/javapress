<%--
  Created by IntelliJ IDEA.
  User: Lancer
  Date: 2017/4/30
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
        + request.getServerName() + ":" + request.getServerPort()
        + path + "/";
%>
<html>
<head>
    <script type="text/javascript">
        window.top.location.href="<%=basePath%>/login.jsp";
    </script>
</head>
</html>