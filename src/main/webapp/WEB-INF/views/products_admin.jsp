<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Products</title>
</head>
<body>

<button><a href="/admin/add/product">Добавить товар</a></button>
<br>
<table border="1">

    <tr>
        <th>Наименование</th>
        <th>Описание</th>
        <th>Цена</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td><a href="/admin/change/product?id=${product.id}">Изменить</a></td>
            <td>
                <button><a href="/admin/delete/product?id=${product.id}">Удалить</a></button>
            </td>
        </tr>
    </c:forEach>

</table>
<br>
<button><a href="/admin/users">Пользователи</a></button>

</body>
</html>
