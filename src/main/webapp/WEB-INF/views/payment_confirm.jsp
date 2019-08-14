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

<button><a href="/user/product">На главную</a></button>
</body>
</html>
