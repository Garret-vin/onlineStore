<%--
  Created by IntelliJ IDEA.
  User: Garret
  Date: 15.07.2019
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm</title>
</head>
<body>

${message}

<form action="/payment/confirm" method="post">
    Введите пароль<input type="text" name="confirm">
    <input type="submit" value="Отправить">
</form>

<button><a href="/user/products">На главную</a></button>
</body>
</html>
