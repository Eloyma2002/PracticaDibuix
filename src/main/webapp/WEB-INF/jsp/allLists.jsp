<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Lists</title>
</head>

<body>
    <h1>List all drawings </h1>
    <a href="/allList">List all drawings | </a>
    <a href="http://">List my drawings | </a>
    <a href="/geoform">Draw</a>
    <p></p>

    <table>
        <tr>
            <td>Date</td>
            <td>Name</td>
            <td>User</td>
            <td>Number Objects</td>
            <td>Actions</td>
        </tr>

        <c:forEach var="drawing" items="${drawings}">

<tr>
            <td>${drawing.getDate()}</td>
            <td>${drawing.getName()}</td>
            <td>${drawing.getUser().getUserName()}</td>
            <td>${drawing.getNumFigures()}</td>
            <td><button>View</button><button>Modify</button><button>Delete</button></td>
</tr>
        </c:forEach>

    </table>
