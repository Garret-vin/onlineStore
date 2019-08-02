<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>

<form action="/admin/add/product" method="post">
    Название <input type="text" name="name"> <br>
    Описание <input type="text" name="description"> <br>
    Цена <input type="number" step="0.01" min="0" placeholder="0,00" name="price"> <br>
    <input type="submit" value="Добавить товар"></form>
</form>

<button><a href="/admin/products">Вернуться</a></button>

</body>
</html>
