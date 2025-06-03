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
<form id="expenseForm">
    <label for="value">Valor: </label>
    <input type="number" id="value" name="value" step="0.01" value="${expense.value}" required>
    <label for="date">Data: </label>
    <input id="date" name="date" type="date" value="${expense.expense_date}" required>
    <label for="category">Categoria: </label>
    <select id="category" name="category">
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
    <label for="description">Descrição: </label>
    <input type="text" name="description" id="description" value="${expense.description}" required>
    <input type="hidden" name="expenseId" value="${expense.id}">
    <input type="hidden" name="userBankId" value="${userBankId}">
    <button type="button" onclick="submitPutForm()">Enviar</button>
</form>
<a href="/finance-control/bank-info?userBankId=${userBankId}">Voltar</a>
</body>
<script>
    function submitPutForm() {
        const form = document.getElementById("expenseForm");
        const formData = new FormData(form);

        const params = new URLSearchParams();
        for (const [key, value] of formData.entries()) {
            params.append(key, value);
        }

        fetch('/finance-control/expenses', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        }).then(response => {
            window.location.href = "/finance-control/bank-info?userBankId=${userBankId}";
        });
    }
</script>
</html>
