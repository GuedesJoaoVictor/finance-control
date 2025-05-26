<<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Vincular Banco</title>
</head>
<body>
    <h1>Vincular Banco</h1>

    <form>
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
        <label for="initial_balance">Valor Inicial: </label>
        <input type="number" name="initial_balance" id="initial_balance" min="0" placeholder="Ex: 1000" required>
    </form>
</body>
</html>
