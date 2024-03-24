<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Товары</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Добавляем стили для кнопки */
        .btn-cart {
            position: fixed;
            top: 0;
            right: 0;
            margin: 10px;
        }
    </style>
</head>

<body>
<div>
    <form method="post" action="http://localhost:8080/apps/dispatcherServlet">
        <div class="container">
            <button type="submit" name="bucket" formaction="http://localhost:8080/apps/dispatcherServlet" formmethod="post" class="btn btn-primary btn-cart">Корзина</button>
        </div>
        <div>
            <h2>Товары</h2>
            <ul>
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
            </ul>
            <button type="submit" name="allClient">Все товары</button>
            <input type="text" name="idProduct" placeholder="id">
            <input type="text" name="ProductCount" placeholder="count">
            <button type="submit" name="addProductByBucket">Добавить в корзину</button>

        </div>
    </form>

</div>



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>