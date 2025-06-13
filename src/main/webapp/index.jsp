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
<div style="height: 100vh; width: 100vw" class="d-flex justify-content-center align-items-center">
    <div class="card text-center" style="width: 30vw;">
        <div class="card-body">
            <h5 class="card-title text-center">Registrar</h5>
            <form action="/finance-control/user" method="post">
                <div class="d-flex justify-content-center align-items-center flex-column mt-4">
                    <label for="name" class="form-label">Nome: </label>
                    <input id="name" type="text" name="name" class="form-control" style="width: 70%;" required>
                </div>
                <div class="d-flex justify-content-center align-items-center flex-column mt-4">
                    <label for="email" class="form-label">Email: </label>
                    <input id="email" type="email" name="email" class="form-control" style="width: 70%;" required>
                </div>
                <div class="d-flex justify-content-center align-items-center flex-column mt-4">
                    <label for="password" class="form-label">Password: </label>
                    <input id="password" type="password" name="password" class="form-control" style="width: 70%;" required>
                </div>
                <div class="d-flex justify-content-center align-items-center flex-column mt-4">
                    <label for="cpf" class="form-label">Cpf: </label>
                    <input id="cpf" name="cpf" type="text" class="form-control" style="width: 70%;" required placeholder="000.000.000-00" maxlength="14">
                </div>
                <button class="mt-4 btn btn-primary" type="submit">Enviar</button>
            </form>
            <div>
                Já possui conta?
                <a href="./login.jsp">Clique aqui.</a>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function cpfMask(value) {
        return value
            .replace(/\D/g, '') // Remove tudo que não for dígito
            .replace(/(\d{3})(\d)/, '$1.$2') // Coloca o primeiro ponto
            .replace(/(\d{3})(\d)/, '$1.$2') // Segundo ponto
            .replace(/(\d{3})(\d{1,2})$/, '$1-$2'); // Hífen
    }

    document.addEventListener("DOMContentLoaded", function () {
        const cpfInput = document.getElementById("cpf");

        cpfInput.addEventListener("input", function (e) {
            const value = e.target.value;
            e.target.value = cpfMask(value);
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</html>
