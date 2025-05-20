<%@ page contentType="text/html; charset=ISO-8859-1" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <c:if test="${not empty user}">
      <h1>Hello ${user.getName()}</h1>
    </c:if>
</body>
</html>
