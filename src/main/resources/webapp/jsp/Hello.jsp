<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Пользователи</title>
</head>

<body>
<table>
    <c:forEach var="email" items="${email}">
        <tr>
            <td>Привет: ${email}</td>
        </tr>
    </c:forEach>
</table>



</body>

</html>