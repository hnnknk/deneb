<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Периферийные устройства</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/error.css' />" rel="stylesheet"/>
</head>
<body ng-app="myApp" class="ng-cloak">

<nav class="navbar navbar-default" ng-controller="mainCtrl">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Deneb</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Компоненты
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#!monitor">Мониторы</a></li>
                    <li><a href="#!mouse">Мышки</a></li>
                    <li><a href="#!keyboard">Клавиатуры</a></li>
                    <li><a href="#!ups">Ибп</a></li>
                </ul>
            </li>
            <li><a href="#!notification">Панель администратора</a></li>
            <li><a href="#" ng-click="logout()">Выход</a></li>
        </ul>
    </div>
</nav>

<div ng-view></div>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
<script src="https://code.angularjs.org/1.6.7/angular-cookies.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular-resource.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular-route.min.js"></script>

<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/interceptors/errorInterceptor.js' />"></script>
<script src="<c:url value='/static/js/interceptors/headerInterceptor.js' />"></script>
<script src="<c:url value='/static/js/controller/oauth_controller.js' />"></script>
<script src="<c:url value='/static/js/service/monitor_service.js' />"></script>
<script src="<c:url value='/static/js/controller/monitor_controller.js' />"></script>
<script src="<c:url value='/static/js/service/ups_service.js' />"></script>
<script src="<c:url value='/static/js/controller/ups_controller.js' />"></script>
<script src="<c:url value='/static/js/service/keyboard_service.js' />"></script>
<script src="<c:url value='/static/js/controller/keyboard_controller.js' />"></script>
<script src="<c:url value='/static/js/service/mouse_service.js' />"></script>
<script src="<c:url value='/static/js/controller/mouse_controller.js' />"></script>
<script src="<c:url value='/static/js/service/notification_service.js' />"></script>
<script src="<c:url value='/static/js/controller/notification_controller.js' />"></script>
<script src="<c:url value='/static/js/others/errors.js' />"></script>
</body>
</html>
