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
  <div class="d-flex justify-content-center align-items-center flex-column mt-4">
    <h2 class="text-center mt-4">Adicionar receita</h2>
    <div class="card p-4 mt-4">
      <div class="card-body">
        <form method="post" action="/revenues">
          <div class="d-flex justify-content-center align-items-center flex-column m-2">
            <label class="form-label" for="value">Valor: </label>
            <input class="form-control" type="number" id="value" name="value" step="0.01" required>
          </div>
          <div class="d-flex justify-content-center align-items-center flex-column m-2">
            <label class="form-label" for="date">Data: </label>
            <input class="form-control" id="date" name="receipt_date" type="date" required>
          </div>
          <div class="d-flex justify-content-center align-items-center flex-column m-2">
            <label class="form-label" for="category">Categoria: </label>
            <select class="form-select" id="category" name="category_id">
              <c:forEach items="${categories}" var="category">
                <option value="${category.id}">${category.name}</option>
              </c:forEach>
            </select>
          </div>
          <div class="d-flex justify-content-center align-items-center flex-column m-2">
            <label class="form-label" for="description">Descrição: </label>
            <input class="form-control" type="text" name="description" id="description" required>
          </div>
          <input type="hidden" name="bank_id" value="${bank.getId()}">
          <input type="hidden" name="userBankId" value="${userBankId}">
          <div class="d-flex justify-content-center align-items-center flex-column m-2">
            <button class="btn btn-primary" type="submit">Enviar</button>
          </div>
        </form>
      </div>
    </div>
    <a class="btn btn-secondary m-4" href="/bank-info/${userBankId}">Voltar</a>
  </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</html>
