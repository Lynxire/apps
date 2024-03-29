<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Админ панель</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container vh-100 d-flex justify-content-center align-items-center">
    <div class="row">
        <button type="submit" class="btn btn-primary col-8" ><a style="text-decoration:none; color: white;" href="http://localhost:8080/apps/jsp/admin/UserUpdate.jsp">Пользователи</a></button>
        <button type="submit" class="btn btn-secondary col-8"><a style="text-decoration:none; color: white;" href="http://localhost:8080/apps/jsp/admin/ProductUpdate.jsp">Товары</a></button>
    </div>
</div>

</body>

</html>