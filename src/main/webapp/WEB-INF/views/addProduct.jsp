<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>

<form:form action="/admin/product/add"
           method="post" modelAttribute="product">
    <table>
        <tr>
            <td>Name</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><form:input path="price"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Подтвердить"></td>
        </tr>
    </table>
</form:form>

<button><a href="/admin/product">Вернуться</a></button>

</body>
</html>
