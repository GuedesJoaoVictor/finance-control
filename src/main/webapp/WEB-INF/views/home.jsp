<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/avif" href="../../assets/finance-control-icon.avif">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
    <title>Finance Control</title>
</head>

<body>
<c:if test="${not empty user}">
    <h1>Olá ${user.getName()}!</h1>
    <a href="/finance-control/logout">Sair</a>
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
                    <a href="/finance-control/bank-info?userBankId=${bank.id}">${bank.name}</a> - ${bank.totalAmount}
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</html>
