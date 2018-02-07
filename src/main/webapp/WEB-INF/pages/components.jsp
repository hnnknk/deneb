<%@ page contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Периферийные устройства</title>
    <base href="/">

    <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/error.css' />" rel="stylesheet"/>
</head>
<body ng-app="myApp" class="ng-cloak">

<nav class="navbar navbar-default" ng-controller="navCtrl">
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
            <li data-ng-if="islogged"><a href="#!notification">Панель администратора</a></li>
            <li data-ng-if="!islogged"><a href="#!login">Вход в систему</a></li>
            <li data-ng-if="islogged"><a href="#" ng-click="logout()">Выход</a></li>
        </ul>
    </div>
</nav>

<div ng-view></div>

<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="webjars/angularjs/1.6.8/angular.js"></script>
<script src="webjars/angularjs/1.6.8/angular-cookies.min.js"></script>
<script src="webjars/angularjs/1.6.8/angular-resource.min.js"></script>
<script src="webjars/angularjs/1.6.8/angular-route.min.js"></script>

<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/interceptors/errorInterceptor.js' />"></script>
<script src="<c:url value='/static/js/interceptors/headerInterceptor.js' />"></script>
<script src="<c:url value='/static/js/controller/oauth_controller.js' />"></script>
v<script src="<c:url value='/static/js/controller/navbar_controller.js' />"></script>
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
<script src="<c:url value='/static/js/controller/ro_controller.js' />"></script>
<script src="<c:url value='/static/js/service/user_service.js' />"></script>
<script src="<c:url value='/static/js/others/errors.js' />"></script>
</body>
</html>
