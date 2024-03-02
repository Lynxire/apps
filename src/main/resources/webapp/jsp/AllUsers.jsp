<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<html>

<head>
    <title>Пользователи</title>
</head>

<body>
<div>
    <form method="post" action="http://localhost:8080/apps/dispatcherServlet">
        <div>
            <h2>Пользователи</h2>
            <button type="submit" name="allUsers">Все пользователи</button>
        </div>
    </form>

</div>
${users}



</body>

</html>