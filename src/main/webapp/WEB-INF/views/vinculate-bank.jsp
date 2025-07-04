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
<div style="width: 100vw; height: 100vh;">
    <div class="d-flex justify-content-end align-items-center m-4">
        <a href="/logout" class="btn btn-danger" style="width: 5%;">Sair</a>
    </div>
    <h1 class="text-center">Vincular Banco</h1>
    <div class="d-flex justify-content-center align-items-center flex-column mt-4">
        <form method="post" action="/vinculate-bank">
            <h2>Bancos disponiveis:</h2>
            <div class="mt-4 d-flex justify-content-center align-items-center flex-column w-100">
                <label for="bank" class="form-label">Bancos:</label>
                <select name="bank" id="bank" class="form-select" required>
                    <option value="Banco do Brasil">Banco do Brasil</option>
                    <option value="NuBank">NuBank</option>
                    <option value="Ita�">Ita�</option>
                    <option value="Santander">Santander</option>
                    <option value="Bradesco">Bradesco</option>
                    <option value="Caixa">Caixa</option>
                </select>
                <input type="hidden" name="cpf" value="${user.getCpf()}">
                <button type="submit" class="btn btn-primary mt-4">Enviar</button>
            </div>
        </form>
        <a href="/home" class="btn btn-secondary">Home</a>
        <c:if test="${not empty message}">
            <div>
                <p class="alert alert-danger mt-4">${message}</p>
            </div>
        </c:if>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</html>