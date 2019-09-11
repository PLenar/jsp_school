<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
    <style>
        <%@ include file="/css/style.css"%>
    </style>
</head>
<body>
<header>
    <ul>
        <li><a href="<c:url value="/"><</c:url>">Strona glowna</a><br/></li>
        <li><a href="<c:url value="/groupPage"><</c:url>">Grupy</a><br/></li>
        <li><a href="<c:url value="/admin"><</c:url>">Panel administratora</a></li>
    </ul>
</header>
<main style="margin-left:20%;padding:1px 16px;height:1000px;">
    <h1>Zarządzanie grupami użytkowników</h1>
    <table>
        <thead style="background-color: #b194a1; color: white; font-size: larger;">
        <td>Nazwa grupy</td>
        <td>Akcje</td>
        </thead>
        <c:forEach var="group" items="${groups}">
            <tr>
                <td>${group.name}</td>
                <td><button><a href="<c:url value="editGroup?id=${group.id}"><</c:url>">Edytuj</a></button><button><a href="<c:url value="deleteGroup?id=${group.id}"><</c:url>">Usuń</a></button></td>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <button><a href="<c:url value="addGroup"></c:url>">Dodaj nową grupę</a></button>
</main>
<footer>
</footer>
</body>
</html>