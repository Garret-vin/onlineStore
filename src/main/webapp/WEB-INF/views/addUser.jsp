<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

${error}

<form action="/admin/add/user" method="post">
    Login <input type="text" name="login" value="${enteredLogin}"> <br>
    E-mail <input type="email" name="email" value="${enteredEmail}"> <br>
    Password <input type="password" name="password"> <br>
    Confirm password <input type="password" name="confirm"> <br>
    Выберите роль:<br>
    <input type="radio" name="role" value="user">Пользователь<br>
    <input type="radio" name="role" value="admin">Администратор<br>
    <input type="submit" value="Зарегистрировать">
</form>

<button><a href="/index.jsp">Выйти</a></button>

</body>
</html>
