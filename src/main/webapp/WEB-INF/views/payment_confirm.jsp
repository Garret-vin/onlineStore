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

<form action="/user/payment/confirm" method="post">
    <table>
        <tr>
            <td>Введите пароль</td>
            <td><input type="text" name="confirm"></td>
            <td><input type="submit" value="Отправить"></td>
        </tr>
    </table>
</form>

<button><a href="/user/products">На главную</a></button>
</body>
</html>
