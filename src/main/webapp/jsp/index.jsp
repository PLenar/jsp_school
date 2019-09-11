<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>CodingSchool</title>
    <meta charset="utf-8">
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
    <h1>Ostatnie rozwiazania</h1>
    <hr/>
    <table>
        <thead style="background-color: #b194a1; color: white; font-size: larger;">
        <td>Tytul</td>
        <td>Autor</td>
        <td>Data dodania</td>
        <td>Akcje</td>
        </thead>
        <c:forEach items="${solutions}" var="solution">
            <tr>
                <td>${solution.title}</td>
                <td>${solution.author}</td>
                <td>${solution.created}</td>
                <td><button><a href="<c:url value="excerciseSolution?id=${solution.id}"></c:url>">Szczegoly</a></button></td>
            </tr>
        </c:forEach>
    </table>
</main>
<footer>
    <%@include file="../WEB-INF/footer.jsp" %>
</footer>
</body>
</html>