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
    <title>Login</title>
</head>
<body>

<h1>Login</h1>

<form action="/login" method="post">

    <p>
        User name:
    </p>
    <input type="text" name="userName"/>

    <p>
        Password:
    </p>
    <input type="password" name="password"/>

    <p><input type="submit" value="Login"></p>
      <c:if test="${!empty error}">
        ${error}
      </c:if>

    <a href="/register"><p>Register</p></a>
</form>

</body>
</html>