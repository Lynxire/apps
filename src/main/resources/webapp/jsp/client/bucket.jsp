<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Товары</title>
</head>

<body>
<div>
    <form method="post" action="http://localhost:8080/apps/dispatcherServlet">
        <div>
            <h2>Корзина</h2>
            <button type="submit" name="makeOrder">Оформить заказ</button>

        </div>
    </form>
</div>
<c:forEach var="product" items="${products}">
    ID - <c:out value="${product.id}" />
    <br>
    Товар - <c:out value="${product.name}" />
    <br>
    Цена - <c:out value="${product.sum}" />
    <br>
    Количество - <c:out value="${product.quantity}" />
    <h1>-------------------------------</h1>

</c:forEach>



</body>

</html>