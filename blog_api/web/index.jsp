<%--
  Created by IntelliJ IDEA.
  User: sj
  Date: 2021/2/22
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/user/login" method="post">
<%--      用户名：<input type="text" name="uName" required><br>--%>
      密码：<input type="text" name="token" required><br>
      <input type="submit" value="登录"><br>
  </form>
<%--  <form action="${pageContext.request.contextPath}/user/register" method="post">--%>
<%--      用户名：<input type="text" name="uName" required><br>--%>
<%--      密码：<input type="text" name="uPassword" required><br>--%>
<%--      <input type="submit" value="注册"><br>--%>
<%--  </form>--%>
  </body>
</html>