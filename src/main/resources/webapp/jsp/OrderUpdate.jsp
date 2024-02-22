<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Редактор товаров</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>

<body>
<div class="container mt-5">
    <h1>Product Management</h1>
    <!-- Форма для редактирования пользователя -->
    <form method="post">
        <div class="mb-3">
            <label for="userId" class="form-label">Product ID</label>
            <input type="text" class="form-control" id="userId" name="id">
        </div>
        <!-- Другие поля ввода для редактирования пользователя -->
        <!-- Кнопки для добавления, удаления и поиска -->
        <button type="submit" class="btn btn-primary" name="add" value="add"  formaction="http://localhost:8080/apps/productUpdate" formmethod="post">Add Product</button>
        <button type="submit" class="btn btn-danger" name="delete" formaction="http://localhost:8080/apps/productUpdate" formmethod="post">Delete Product</button>
        <button type="submit" class="btn btn-info" name="search" value="search"  formaction="http://localhost:8080/apps/productUpdate" formmethod="post">Search by ID</button>
        <button type="submit" class="btn btn-info" name="all" value="all"  formaction="http://localhost:8080/apps/productUpdate" formmethod="post">All Product</button>
        <br>
        <br>
        <br>
        <input type="text" class="" id="sum" name="sum" placeholder="sum">
        <input type="text" class="" id="code" name="code" placeholder="code">
        <input type="text" class="" id="name" name="name" placeholder="name">
        <input type="text" class="" id="quantity" name="quantity" placeholder="quantity">

    </form>
</div>
${id}
${products}



</body>

</html>