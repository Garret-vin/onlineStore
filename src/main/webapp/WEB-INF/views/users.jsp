<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>


<div align="left">

    <button><a href="/admin/add/user">Добавить пользователя</a></button>

    <table border="1">
        <tr>
            <td>Email</td>
            <td>Login</td>
            <td>Password</td>
        </tr>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td>${user.email}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>
                    <button><a href="/admin/change/user?id=${user.id}">Изменить</a></button>
                </td>
                <td>
                    <button><a href="/admin/delete/user?id=${user.id}">Удалить</a></button>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <button><a href="/admin/products">Товары</a></button>
    <button><a href="/">Выйти</a></button>

</div>
</body>
</html>
