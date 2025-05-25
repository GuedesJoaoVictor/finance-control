<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
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
