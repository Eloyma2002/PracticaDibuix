<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Lists</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body>
    <h1>List all drawings </h1>
    <a href="/allLists">List all drawings | </a>
    <a href="/myList">List my drawings | </a>
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
            <td>
                <form action="/viewDrawing" method="post">
                    <input type="hidden" name="drawingId" value="${drawing.getId()}">
                    <input type="submit" name="action" value="View"></button>
                </form>
                <form action="/modifyDrawing" method="post">
                    <input type="hidden" name="drawingId" value="${drawing.getId()}">
                    <input type="submit" name="action" value="Modify"></button>
                </form>
                <form action="/allLists" method="post">
                    <input type="hidden" name="drawingId" value="${drawing.getId()}">
                    <input type="submit" name="action" value="Delete"></button>
                </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</html>