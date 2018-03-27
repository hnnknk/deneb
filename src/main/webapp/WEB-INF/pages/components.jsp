<%@ page contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Периферийные устройства</title>
    <base href="/">

    <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/error.css' />" rel="stylesheet"/>
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="webjars/angularjs/1.6.8/angular.min.js"></script>
    <script src="webjars/angularjs/1.6.8/angular-cookies.min.js"></script>
    <script src="webjars/angularjs/1.6.8/angular-resource.min.js"></script>
    <script src="webjars/angularjs/1.6.8/angular-route.min.js"></script>
    <script src="webjars/angularjs/1.6.8/angular-animate.min.js"></script>

    <script src="<c:url value='/static/js/app.js' />"></script>
    <script src="<c:url value='/static/js/interceptors/errorInterceptor.js' />"></script>
    <script src="<c:url value='/static/js/interceptors/headerInterceptor.js' />"></script>
    <script src="<c:url value='/static/js/controller/oauth_controller.js' />"></script>
    <script src="<c:url value='/static/js/controller/navbar_controller.js' />"></script>
    <script src="<c:url value='/static/js/service/user_service.js' />"></script>
    <script src="<c:url value='/static/js/others/errors.js' />"></script>
</head>
<body ng-app="myApp" class="ng-cloak">

<nav class="navbar navbar-default" ng-controller="navCtrl">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Deneb</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li data-ng-if="islogged"><a href="#!computer">Компьютер</a></li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Системный блок
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li data-ng-if="!islogged"><a href="#!roHdd">Жесткие диски</a></li>
                    <li data-ng-if="islogged"><a href="#!hdd">Жесткие диски</a></li>
                    <li data-ng-if="!islogged"><a href="#!roRam">Оперативная память</a></li>
                    <li data-ng-if="islogged"><a href="#!ram">Оперативная память</a></li>
                    <li data-ng-if="!islogged"><a href="#!roMotherboard">Материнские платы</a></li>
                    <li data-ng-if="islogged"><a href="#!motherboard">Материнские платы</a></li>
                    <li data-ng-if="!islogged"><a href="#!roProcessor">Процессоры</a></li>
                    <li data-ng-if="islogged"><a href="#!processor">Процессоры</a></li>
                    <li data-ng-if="!islogged"><a href="#!roPowersupply">Блоки питания</a></li>
                    <li data-ng-if="islogged"><a href="#!powersupply">Блоки питания</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown">Компоненты
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li data-ng-if="!islogged"><a href="#!roMonitor">Мониторы</a></li>
                    <li data-ng-if="islogged"><a href="#!monitor">Мониторы</a></li>
                    <li data-ng-if="!islogged"><a href="#!roMouse">Мышки</a></li>
                    <li data-ng-if="islogged"><a href="#!mouse">Мышки</a></li>
                    <li data-ng-if="!islogged"><a href="#!roKeyboard">Клавиатуры</a></li>
                    <li data-ng-if="islogged"><a href="#!keyboard">Клавиатуры</a></li>
                    <li data-ng-if="!islogged"><a href="#!roUps">Ибп</a></li>
                    <li data-ng-if="islogged"><a href="#!ups">Ибп</a></li>
                </ul>
            </li>
            <li data-ng-if="islogged"><a href="#!notification">Панель администратора</a></li>
            <li data-ng-if="!islogged"><a href="#!login">Вход в систему</a></li>
            <li data-ng-if="islogged"><a href="#" ng-click="logout()">Выход</a></li>
        </ul>
    </div>
</nav>

<div ng-view></div>

<script src="<c:url value='/static/js/controller/computer_controller.js' />"></script>
<script src="<c:url value='/static/js/service/computer_service.js' />"></script>
<script src="<c:url value='/static/js/controller/ro_controller.js' />"></script>
<script src="<c:url value='/static/js/service/monitor_service.js' />"></script>
<script src="<c:url value='/static/js/controller/monitor_controller.js' />"></script>
<script src="<c:url value='/static/js/service/ups_service.js' />"></script>
<script src="<c:url value='/static/js/controller/ups_controller.js' />"></script>
<script src="<c:url value='/static/js/service/keyboard_service.js' />"></script>
<script src="<c:url value='/static/js/controller/keyboard_controller.js' />"></script>
<script src="<c:url value='/static/js/service/mouse_service.js' />"></script>
<script src="<c:url value='/static/js/controller/mouse_controller.js' />"></script>
<script src="<c:url value='/static/js/service/hdd_service.js' />"></script>
<script src="<c:url value='/static/js/controller/hdd_controller.js' />"></script>
<script src="<c:url value='/static/js/service/notification_service.js' />"></script>
<script src="<c:url value='/static/js/controller/notification_controller.js' />"></script>

<script src="<c:url value='/static/js/service/ram_service.js' />"></script>
<script src="<c:url value='/static/js/controller/ram_controller.js' />"></script>
<script src="<c:url value='/static/js/service/processor_service.js' />"></script>
<script src="<c:url value='/static/js/controller/processor_controller.js' />"></script>
<script src="<c:url value='/static/js/service/motherboard_service.js' />"></script>
<script src="<c:url value='/static/js/controller/motherboard_controller.js' />"></script>
<script src="<c:url value='/static/js/service/powersupply_service.js' />"></script>
<script src="<c:url value='/static/js/controller/powersupply_controller.js' />"></script>
</body>
</html>
