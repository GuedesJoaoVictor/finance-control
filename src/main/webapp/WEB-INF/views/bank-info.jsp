<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html lang="ptt a-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="image/avif" href="../../assets/finance-control-icon.avif">
  <title>Finance Control</title>
</head>

<body>
    <c:if test="${not empty user}">
        <h2>${bank.getName()}</h2>

        <table>
            <thead>
                <tr>
                    <th>Descrição</th>
                    <th>Valor</th>
                    <th>Data</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${revenues}" var="revenue">
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
                    </tr>
                </c:forEach>
                <c:forEach items="${expenses}" var="expense">
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
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty user}">
      <h2>Not Found.</h2>
    </c:if>
</body>
</html>
