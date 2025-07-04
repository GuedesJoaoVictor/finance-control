<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finance Control</title>
    <link rel="icon" type="image/avif" href="../../assets/finance-control-icon.avif">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>

<body>
    <div style="height: 100vh; width: 100vw;" class="d-flex justify-content-center align-items-center flex-column">
        <c:if test="${not empty message}">
            <div class="d-flex justify-content-center align-items-center">
                <div class="alert alert-danger" role="alert">${message}</div>
            </div>
        </c:if>
        <div class="card text-center" style="width: 30vw;">
            <div class="card-body">
                <h5 class="card-title text-center">Login</h5>
                <form action="/login" method="post">
                    <div class="d-flex justify-content-center align-items-center flex-column mt-4">
                        <label for="email" class="form-label">Email:</label>
                        <input type="text" name="email" id="email" class="form-control" style="width: 70%;" required>
                    </div>
                    <div class="d-flex justify-content-center align-items-center flex-column mt-4">
                        <label for="password" class="form-label">Senha:</label>
                        <input type="password" name="password" id="password" class="form-control" style="width: 70%;" required>
                    </div>
                    <button class="mt-4 btn btn-primary" type="submit">Entrar</button>
                </form>
                <div>
                    Nao possui conta?
                    <a href="/">Registrar</a>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</html>
