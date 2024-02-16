<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="by.teachmeskills.entity.*" %>
<html>

<head>
    <title>Пользователи</title>
</head>

<body>
<div>
    <form method="get" action="http://localhost:8080/apps/hello">
        <div>
            <p><input type="text" placeholder="name" name="name"></p>
            <button type="submit">Привет</button>
        </div>
    </form>
</div>
${name}



</body>

</html>