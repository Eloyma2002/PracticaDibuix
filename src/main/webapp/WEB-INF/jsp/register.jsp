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

<h1>Register</h1>

<form action="/register" method="post">

    <p>
        Name and last name:
    </p>
    <input type="text" name="nameAndLastName" required/>

    <p>
        User name:
    </p>
    <input type="text" name="userName" required/>

    <p>
        Password:
    </p>
    <input type="password" name="password" required/>

    <p><input type="submit" value="registert!!"></p>
      <c:if test="${!empty error}">
        ${error}
      </c:if>

    <a href="/login"><p>Login</p></a>
</form>

</body>
</html>