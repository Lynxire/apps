<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<html>

<head>
    <title>Пользователи</title>
</head>

<body>
<div>
    <form method="get" action="http://localhost:8080/apps/users">
        <div>
            <h2>Пользователи</h2>
            <button type="submit">Все пользователи</button>
        </div>
    </form>

</div>
${users}



</body>

</html>