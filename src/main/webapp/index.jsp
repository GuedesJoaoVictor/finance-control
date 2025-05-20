<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>
    <body>
        <h2>Registrar</h2>

        <form action="/finance-control/user" method="post">
            <label for="name">Nome: </label>
            <input id="name" type="text" name="name" required>
            <label for="email">Email: </label>
            <input id="email" type="email" name="email" required>
            <label for="password">Password: </label>
            <input id="password" type="password" name="password" required>
            <label for="cpf">Cpf: </label>
            <input id="cpf" name="cpf" type="text" required>
            <button type="submit">Enviar</button>
        </form>
        <div>
          Já possui conta?
          <a href="./WEB-INF/views/login.jsp">Clique aqui.</a>
        </div>
    </body>
</html>
