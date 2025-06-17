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
    <div style="width: 100vw; height: 100vh;">
        <div class="d-flex justify-content-end align-items-center m-4">
            <a href="/finance-control/logout" class="btn btn-danger" style="width: 5%;">Sair</a>
        </div>

        <div class="d-flex justify-content-center align-items-center flex-column" >
            <div class="d-flex justify-content-center align-items-center flex-column m-4">
                <h2 class="text-center">${bank.getName()}</h2>
            </div>
            <h2 class="m-4">Receitas</h2>
            <a class="btn btn-primary m-4" href="/finance-control/revenues?userBankId=${userBank.getId()}">Adicionar Receita</a>
            <div class="table-responsive m-4">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Descrição</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Data</th>
                        <th scope="col">Ação</th>
                    </tr>
                    </thead>
                    <c:forEach items="${revenues}" var="revenue">
                        <tbody>
                        <tr>
                            <th scope="row">
                                    ${revenue.description}
                            </th>
                            <td>
                                    ${revenue.value}
                            </td>
                            <td>
                                    ${revenue.receipt_date}
                            </td>
                            <td>
                                <button class="btn btn-secondary" onclick="onClickRedirectToEditRevenue(${revenue.id})">Editar</button>
                                <button class="btn btn-danger" onclick="onClickDeleteRevenue(${revenue.id})">Remover</button>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                    <c:if test="${not empty revenuesEmpty}">
                        <tfoot>
                        <tr class="text-center">
                            <td colspan="4">
                                <h3 class="alert alert-danger">${revenuesEmpty}</h3>
                            </td>
                        </tr>
                        </tfoot>
                    </c:if>
                </table>
            </div>

            <h2 class="m-4">Gastos</h2>
            <a class="btn btn-primary m-4" href="/finance-control/expenses?userBankId=${userBank.getId()}">Adicionar Gasto</a>
            <div class="table-responsive m-4">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Descrição</th>
                        <th scope="col">Valor</th>
                        <th scope="col">Data</th>
                        <th scope="col">Ação</th>
                    </tr>
                    </thead>
                    <c:forEach items="${expenses}" var="expense">
                        <tbody>
                        <tr>
                            <th scope="row">
                                    ${expense.description}
                            </th>
                            <td>
                                    ${expense.value}
                            </td>
                            <td>
                                    ${expense.expense_date}
                            </td>
                            <td>
                                <button class="btn btn-secondary" onclick="onClickRedirectToEditExpense(${expense.id})">Editar</button>
                                <button class="btn btn-danger" onclick="onClickDeleteExpense(${expense.id})">Remover</button>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                    <c:if test="${not empty expensesEmpty}">
                        <tfoot>
                        <tr class="text-center">
                            <td colspan="4">
                                <h3 class="alert alert-danger">${expensesEmpty}</h3>
                            </td>
                        </tr>
                        </tfoot>
                    </c:if>
                </table>
            </div>
            <a class="btn btn-secondary" href="/finance-control/home">Home</a>
            </div>
        </div>

</c:if>
<c:if test="${empty user}">
    <h2 class="text-center">Not Found.</h2>
</c:if>
</body>
<script>
    function onClickDeleteRevenue(id) {
        if (confirm("Realmente deseja remover essa receita?")) {
            fetch("/finance-control/revenues", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "id=" + id
            }).then(response => {
                location.replace("http://localhost:8080/finance-control/bank-info?userBankId=" + "${userBank.getId()}");
            })
        }
    }
    function onClickDeleteExpense(id) {
        if (confirm("Realmente deseja remover esse gasto?")) {
            fetch("/finance-control/expenses", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "id=" + id
            }).then(response => {
                location.replace("http://localhost:8080/finance-control/bank-info?userBankId=" + "${userBank.getId()}");
            })
        }
    }

    function onClickRedirectToEditExpense(id) {
        window.location.href = "/finance-control/change-expenses?userBankId=${userBank.getId()}&id=" + id;
    }

    function onClickRedirectToEditRevenue(id) {
        window.location.href = "/finance-control/change-revenues?userBankId=${userBank.getId()}&id=" + id;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</html>
