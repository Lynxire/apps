<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Товары</title>
</head>

<body>
${orders}
<div>
    <form method="post" action="http://localhost:8080/apps/dispatcherServlet">
        <div>
            <h2>Корзина</h2>
            <button type="submit" name="makeOrder">Оформить заказ</button>
            <button type="submit" name="cleanBucket">Очистить корзину</button>

        </div>
    </form>
</div>




</body>

</html>