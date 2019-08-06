<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Оплата товара</title>
</head>
<body>
<h1>Создание заказа</h1>

<form:form action="/user/payment" method="post" modelAttribute="order">
    <table>
        <tr>
            <td>E-mail:</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Номер телефона:</td>
            <td><form:input path="phoneNumber"/></td>
        </tr>
        <tr>
            <td>Адрес доставки:</td>
            <td><form:input path="address"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Подтвердить заказ"/></td>
        </tr>
    </table>
</form:form>

</body>
</html>
