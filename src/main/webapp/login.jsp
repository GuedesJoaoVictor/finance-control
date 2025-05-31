<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finance Control</title>
    <link rel="icon" type="image/avif" href="../../assets/finance-control-icon.avif">
</head>

<body>
    <h1>Login</h1>
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
    <form action="/finance-control/login" method="post">
        <label for="email">Email:</label>
        <input type="text" name="email" id="email">
        <label for="password">Senha:</label>
        <input type="password" name="password" id="password">
        <button type="submit">Entrar</button>
    </form>
    <a href="./index.jsp">Registrar</a>
</body>
</html>
