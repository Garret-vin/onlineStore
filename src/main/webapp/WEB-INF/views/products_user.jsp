<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Товары</title>
</head>
<body>


<p>Количество товара в корзине: ${size} <br>
    <button><a href="/user/payment">Корзина</a></button>
</p>

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
            <td><a href="/user/product/buy/${product.id}">Купить</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<button><a href='<spring:url value="/signout"/>'>Выйти</a></button>
</body>
</html>
