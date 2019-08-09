<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<button><a href="/admin/user/add">Добавить пользователя</a></button>

<table border="1">
    <tr>
        <td>Email</td>
        <td>Login</td>
        <td>Password</td>
        <td>Role</td>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.email}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td>
                <button><a href="/admin/user/change/${user.id}">Изменить</a></button>
            </td>
            <td>
                <button><a href="/admin/user/delete/${user.id}">Удалить</a></button>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<button><a href="/admin/product">Товары</a></button>
<button><a href='<spring:url value="/signout"/>'>Выйти</a></button>
</body>
</html>
