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
<c:if test="${not empty user}">
    <h1>Olá ${user.getName()}!</h1>
    <button>
        <a href="/finance-control/vinculate-bank">Adicionar banco</a>
    </button>
    <p>Bem vindo ao controle de financas!</p>
    <c:if test="${userBanks == []}">
        <p>Você ainda não possui contas cadastradas.</p>
    </c:if>
    <c:if test="${userBanks != []}">
        <h2>Suas contas:</h2>
        <ul>
            <c:forEach items="${userBanks}" var="bank">
                <li>
                    <a href="/finance-control/bank-info?userBankId=${bank.id}">${bank.name}</a> - ${bank.initial_balance}
                    <button onclick="unlinkAccount(${bank.id})">Remover Conta</button>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</c:if>
</body>
<script>
    function unlinkAccount(id) {
        if (confirm("Deseja realmente deletar esse banco?")) {
            fetch("/finance-control/vinculate-bank", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: "id=" + id
            }).then(response => {
                location.replace("http://localhost:8080/finance-control/home");
            });
        }
    }
</script>
</html>
