<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change product</title>
</head>
<body>

${error}

<form action="/admin/change/product?id=${productId}" method="post">
    Название <input type="text" name="name" value="${oldName}"> <br>
    Описание <input type="text" name="description" value="${oldDescription}"> <br>
    Цена <input type="number" step="0.01" min="0" placeholder="0,00" name="price" value="${oldPrice}">
    <br>
    <input type="submit" value="Изменить товар"></form>
</form>

</body>
</html>
