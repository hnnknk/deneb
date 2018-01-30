<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ошибка доступа</title>

    <link href="<c:url value='/static/css/error401.css' />" rel="stylesheet"/>
</head>
<body>

<div class="error-401">
    <img src="/static/images/error401.jpg" alt="error401">
</div>

<script src="<c:url value='/static/js/others/redirectFromError.js' />"></script>
</body>
</html>