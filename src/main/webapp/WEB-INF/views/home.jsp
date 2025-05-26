<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:if test="${not empty user}">
    <h1>Olá ${user.getName()}!</h1>
    <button>
        <a href="./vinculate-bank.jsp">Adicionar banco</a>
    </button>
    <p>Bem vindo ao controle de financas!</p>
    <c:if test="${userBanks == []}">
        <p>Você ainda não possui contas cadastradas.</p>
    </c:if>
    <c:if test="${userBanks != []}">
        <h2>Suas contas:</h2>
        <ul>
            <c:forEach items="${userBanks}" var="bank">
                <li>${bank.name} - ${bank.initial_balance}</li>
            </c:forEach>
        </ul>
    </c:if>
</c:if>
</body>
</html>
