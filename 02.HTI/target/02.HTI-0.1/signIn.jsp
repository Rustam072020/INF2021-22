<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 30.09.2021
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
    <h1>Sign In</h1>
    <form method="post" action="/signIn">
        <input class="text" name = "email" type="text">
        <input class="text" name = "password" type="password">
        <input class="button" name = "Sign in" type="submit">
    </form>
</body>
</html>
