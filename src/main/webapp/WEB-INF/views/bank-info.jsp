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
    <a href="/finance-control/logout">Sair</a>
    <c:if test="${not empty user}">
        <h2>${bank.getName()}</h2>

        <h2>Receitas</h2>
        <a href="/finance-control/revenues?userBankId=${userBank.getId()}">Adicionar Receita</a>
        <table>
            <thead>
                <tr>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>Ação</th>
                </tr>
            </thead>
            <c:forEach items="${revenues}" var="revenue">
                <tbody>
                    <tr>
                        <th>
                                ${revenue.description}
                        </th>
                        <td>
                                ${revenue.value}
                        </td>
                        <td>
                                ${revenue.receipt_date}
                        </td>
                        <td>
                            <button onclick="onClickRedirectToEditRevenue(${revenue.id})">Editar</button>
                            <button onclick="onClickDeleteRevenue(${revenue.id})">Remover</button>
                        </td>
                    </tr>
                </tbody>
            </c:forEach>
            <c:if test="${not empty revenuesEmpty}">
                <tfoot>
                    <tr>
                        <td colspan="3">
                            <h3>${revenuesEmpty}</h3>
                        </td>
                    </tr>
                </tfoot>
            </c:if>
        </table>

        <h2>Gastos</h2>
        <a href="/finance-control/expenses?userBankId=${userBank.getId()}">Adicionar Gasto</a>
        <table>
            <thead>
                <tr>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th>Data</th>
                    <th>Ação</th>
                </tr>
            </thead>
            <c:forEach items="${expenses}" var="expense">
                <tbody>
                    <tr>
                        <th>
                            ${expense.description}
                        </th>
                        <td>
                            ${expense.value}
                        </td>
                        <td>
                            ${expense.expense_date}
                        </td>
                        <td>
                            <button onclick="onClickRedirectToEditExpense(${expense.id})">Editar</button>
                            <button onclick="onClickDeleteExpense(${expense.id})">Remover</button>
                        </td>
                    </tr>
                </tbody>
            </c:forEach>
            <c:if test="${not empty expensesEmpty}">
                <tfoot>
                    <tr>
                        <td>
                            <h3>${expensesEmpty}</h3>
                        </td>
                    </tr>
                </tfoot>
            </c:if>
        </table>
    </c:if>
    <c:if test="${empty user}">
      <h2>Not Found.</h2>
    </c:if>
    <a href="/finance-control/home">Home</a>
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
