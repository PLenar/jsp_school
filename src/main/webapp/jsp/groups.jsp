<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Groups</title>
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
    <h1>Lista grup:</h1>
    <table>
        <thead>
        <td>Nazwa Grupy</td>
        <td>Akcje</td>
        </thead>
        <c:forEach items="${userGroups}" var="userGroup">
            <tr>
                <td>${userGroup.name}</td>
                <td><button><a href="<c:url value="userPage?id=${userGroup.id}"></c:url>">Lista członków</a></button></td>
            </tr>
        </c:forEach>
    </table>
</main>
</body>
</html>
