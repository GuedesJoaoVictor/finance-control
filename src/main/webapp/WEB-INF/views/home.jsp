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
    <div style="height: 100vh; width: 100vw;">
        <div class="d-flex justify-content-end align-items-center m-4">
            <a href="/finance-control/logout" class="btn btn-danger" style="width: 5%;">Sair</a>
        </div>
        <h1 class="text-center">Olá ${user.getName()}!</h1>
        <div class="d-flex justify-content-center align-items-center flex-column mt-4">
            <div class="w-100 text-end pe-4">
                <a href="/vinculate-bank" class="btn btn-primary m-4">Adicionar banco</a>
            </div>
            <h4>Bem vindo ao controle de financas!</h4>
            <c:if test="${userBanks == []}">
                <p class="alert alert-danger m-4">Você ainda não possui contas cadastradas.</p>
            </c:if>
            <c:if test="${userBanks != []}">
                <h2 class="text-center">Suas contas:</h2>
                    <c:forEach items="${userBanks}" var="bank">
                        <div class="d-flex justify-content-between align-items-center m-2 p-2 border">
                            <a href="/bank-info/${bank.id}" class="m-4">${bank.name}</a>
                            <p class="m-4">${bank.totalAmount}</p>
                            <button class="btn btn-danger m-4" onclick="unlinkAccount(${bank.id})">Remover Conta</button>
                        </div>
                    </c:forEach>
            </c:if>
        </div>
    </div> 
</c:if>
</body>
<script>
    function unlinkAccount(id) {
        if (confirm("Deseja realmente deletar esse banco?")) {
            fetch("/vinculate-bank/" + id, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                }
            }).then(response => {
                location.replace("http://localhost:8080/home");
            });
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</html>
