<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/avif" href="../../assets/finance-control-icon.avif">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <title>Finance Control</title>
</head>

<body>
<div style="width: 100vw; height: 100vh;">
    <div class="d-flex justify-content-end align-items-center m-4">
        <a href="/logout" class="btn btn-danger" style="width: 5%;">Sair</a>
    </div>
    <div class="d-flex justify-content-center align-items-center flex-column mt-4">
        <h2 class="text-center mt-4">Editar Receita</h2>
        <div class="card p-4 mt-4">
            <div class="card-body">
                <form id="expenseForm">
                    <div class="d-flex flex-column m-2">
                        <label class="form-label" for="value">Valor: </label>
                        <input class="form-control" type="number" id="value" name="value" step="0.01" value="${revenue.value}" required>
                    </div>
                    <div class="d-flex flex-column m-2">
                        <label class="form-label" for="date">Data: </label>
                        <input class="form-control" id="date" name="date" type="date" value="${revenue.receipt_date}" required>
                    </div>
                    <div class="d-flex flex-column m-2">
                        <label class="form-label" for="category">Categoria: </label>
                        <select class="form-select" id="category" name="category_id">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="d-flex flex-column m-2">
                        <label class="form-label" for="description">Descrição: </label>
                        <input class="form-control" type="text" name="description" id="description" value="${revenue.description}" required>
                    </div>
                    <input type="hidden" name="revenueId" value="${revenue.id}">
                    <input type="hidden" name="userBankId" value="${userBankId}">
                    <div class="d-flex justify-content-center m-2">
                        <button class="btn btn-primary" type="button" onclick="submitPutForm(${revenue.id})">Enviar</button>
                    </div>
                </form>
            </div>
        </div>
        <a class="btn btn-secondary m-4" href="/bank-info/${userBankId}">Voltar</a>
    </div>
</div>

<script>
    function submitPutForm(id) {
        const form = document.getElementById("expenseForm");
        const formData = new FormData(form);
        const params = new URLSearchParams();
        for (const [key, value] of formData.entries()) {
            params.append(key, value);
        }

        fetch('/revenues/' + id, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params
        }).then(response => {
            window.location.href = "/finance-control/bank-info?userBankId=${userBankId}";
        });
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>