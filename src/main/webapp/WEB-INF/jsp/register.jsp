<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
isELIgnored="false"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REGISTRE</title>
</head>
<body>

<form action="/register" method="post">

    <c:if test="${usuariAfegit == true}">
    </c:if>

    <p>
        Nom i cognoms:
    </p>
    <input type="text" name="nomICognoms"/>

    <p>
        Nom d'usuari:
    </p>
    <input type="text" name="nomUsuari"/>

    <p>
        Password:
    </p>
    <input type="password" name="password"/>

    <p><input type="submit" value="registert!!"></p>
</form>

</body>
</html>