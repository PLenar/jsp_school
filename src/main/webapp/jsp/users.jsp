<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserDetails</title>
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
    <h1>Szczegóły użytkownika ${user.username}</h1>
    <div>
        <p>Nazwa: ${user.username}</p>
        <p>Email: ${user.email}</p>
    </div>
    <h3>Dodane rozwiązania zadań:</h3>
    <table>
        <thead style="background-color: #b194a1; color: white;">
        <td>Tytuł zadania</td>
        <td>Data dodania</td>
        <td>Szczegóły zadania</td>
        </thead>
        <c:forEach items="${mainPageSolutions}" var="mainPageSolution">
            <tr>
                <td>${mainPageSolution.title}</td>
                <td>${mainPageSolution.created}</td>
                <td><button><a href="<c:url value="excerciseSolution?id=${mainPageSolution.userId}"></c:url>">Szczegoly</a></button></td>
            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>
