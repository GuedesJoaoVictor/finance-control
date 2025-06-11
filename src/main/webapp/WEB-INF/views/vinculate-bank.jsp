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
    <h1>Vincular Banco</h1>
    <a href="/finance-control/logout">Sair</a>
    <form method="post" action="/finance-control/vinculate-bank">
        <h2>Bancos disponiveis:</h2>
        <label for="bank">Bancos:</label>
        <select name="bank" id="bank" required>
            <option value="Banco do Brasil">Banco do Brasil</option>
            <option value="NuBank">NuBank</option>
            <option value="Itaú">Itaú</option>
            <option value="Santander">Santander</option>
            <option value="Bradesco">Bradesco</option>
            <option value="Caixa">Caixa</option>
        </select>
        <label for="name">Nome da Conta: </label>
        <input type="text" name="name" id="name" placeholder="Ex: Conta Corrente Itaú" required>
        <input type="hidden" name="cpf" value="${user.getCpf()}">
        <button type="submit">Enviar</button>
    </form>

    <a href="/finance-control/home">Home</a>

    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>
</body>
</html>
