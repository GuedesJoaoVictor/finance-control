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
    <a href="/finance-control/logout">Sair</a>
    <form method="post" action="/finance-control/revenues">
        <label for="value">Valor: </label>
        <input type="number" id="value" name="value" step="0.01" required>
        <label for="date">Data: </label>
        <input id="date" name="date" type="date" required>
        <label for="category">Categoria: </label>
        <select id="category" name="category">
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <label for="description">Descrição: </label>
        <input type="text" name="description" id="description" required>
        <input type="hidden" name="bankId" value="${bank.getId()}">
        <input type="hidden" name="userBankId" value="${userBankId}">
        <button type="submit">Enviar</button>
    </form>
    <a href="/finance-control/bank-info?userBankId=${userBankId}">Voltar</a>
</body>
</html>
