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
            <h2>Товары</h2>
            <ul>
                <c:forEach var="product" items="${products}">
                    <li><c:out value="${product}" /></li>
                </c:forEach>
            </ul>
            <button type="submit" name="allClient">Все товары</button>
        </div>
    </form>

</div>




</body>

</html>