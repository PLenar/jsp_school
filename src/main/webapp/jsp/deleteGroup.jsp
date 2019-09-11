<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DeleteGroup</title>
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
    <div>
        <form action="deleteGroup" method="post">
            <input type="hidden" name="groupId" value="${group.id}" />    <%--działa !!--%>
            <p>Chcesz usunąć grupę o nazwie <b>${groupName}</b>.
                Czy jesteś pewien?</p>
            <input type="submit" value="Usuń">
        </form>
        <c:if test="${empty group.id}">
            <h3>Usunięto.</h3>
        </c:if>
    </div>
</main>
<footer>
</footer>
</body>
</html>
