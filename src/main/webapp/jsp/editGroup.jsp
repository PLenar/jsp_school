<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditGroup</title>
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
        <form action="editGroup" method="post">
            <input type="hidden" name="groupId" value="${group.id}" />
            <p>Nowa nazwa grupy <b>${group.name}:</b></p>
            <input name="newName" type="text"/>  <%--działa !!--%>
            <input type="submit" value="Edytuj">
        </form>
    </div>
</main>
<footer>
</footer>
</body>
</html>