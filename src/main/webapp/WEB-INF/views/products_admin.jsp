<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Products</title>
</head>
<body>

<button><a href="/admin/product/add">Добавить товар</a></button>
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
            <td><a href="/admin/product/change/${product.id}">Изменить</a></td>
            <td>
                <button><a href="/admin/product/delete/${product.id}">Удалить</a></button>
            </td>
        </tr>
    </c:forEach>

</table>
<br>
<button><a href="/admin/user">Пользователи</a></button>

</body>
</html>
