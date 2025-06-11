<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/avif" href="../../assets/finance-control-icon.avif">
    <title>Finance Control</title>
</head>
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
            <input id="cpf" name="cpf" type="text" required placeholder="000.000.000-00" maxlength="14">
            <button type="submit">Enviar</button>
        </form>
        <div>
          Já possui conta?
          <a href="./login.jsp">Clique aqui.</a>
        </div>
    </body>
    <script>
        function cpfMask(value) {
            return value
                .replace(/\D/g, '') // Remove tudo que não for dígito
                .replace(/(\d{3})(\d)/, '$1.$2') // Coloca o primeiro ponto
                .replace(/(\d{3})(\d)/, '$1.$2') // Segundo ponto
                .replace(/(\d{3})(\d{1,2})$/, '$1-$2'); // Hífen
        }

        document.addEventListener("DOMContentLoaded", function () {
            const cpfInput = document.getElementById("cpf");

            cpfInput.addEventListener("input", function (e) {
                const value = e.target.value;
                e.target.value = cpfMask(value);
            });
        });
    </script>
</html>
