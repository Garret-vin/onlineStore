<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SHOP</title>
</head>
<body>

<div align="center">

    ${error}

    <form action="/login" method="post">
        Логин <input type="text" name="login"> <br>
        Пароль <input type="password" name="password">
        <br><br>
        <input type="submit" value="Войти">
    </form>

</div>
</body>
</html>
