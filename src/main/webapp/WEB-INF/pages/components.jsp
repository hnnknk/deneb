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

<!-- Alert messages -->
<div class="alert alert-success fade al">
    <strong>Вы успешно добавили периферийное устройство!</strong>
</div>

<div class="confalert alert-danger fade al">
    <strong>Ошибка! Такоe периферийное устройство уже существует.</strong>
</div>

<div class="badalert alert-danger fade al">
    <strong>Ошибка! Введенные данные неккоректны.</strong>
</div>

<div class="upalert alert-success fade al">
    <strong>Вы успешно обновили периферийное устройство!</strong>
</div>


<!-- Images for content changing -->

<div class="icon-container">
    <div class="row">
        <div class="col-sm-3 col-md-3">
            <a href="#!monitor">
            <img class="img-responsive center-block img-pointer" id="mon_icon" src="/static/images/mon.png" class="img-rounded" alt="Monitor" title="Мониторы">
            </a>
        </div>

        <div class="col-sm-3 col-md-3">
            <a href="#!ups">
            <img class="img-responsive center-block img-pointer" id="ups_icon" src="/static/images/ups.png" class="img-rounded" alt="UPS" title="Источники бесперебойного питания">
            </a>
        </div>

        <div class="col-sm-3 col-md-3">
            <a href="#!mouse">
            <img class="img-responsive center-block img-pointer" id="mouse_icon" src="/static/images/mouse.png" class="img-rounded" alt="Mouse" title="Мышки">
            </a>
        </div>

        <div class="col-sm-3 col-md-3">
            <a href="#!keyboard">
            <img class="img-responsive center-block img-pointer" id="key_icon" src="/static/images/keyboard.png" class="img-rounded" alt="Keyboard" title="Клавиатуры">
            </a>
        </div>
    </div>
</div>

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
<script src="<c:url value='/static/js/service/monitor_service.js' />"></script>
<script src="<c:url value='/static/js/controller/monitor_controller.js' />"></script>
<script src="<c:url value='/static/js/service/ups_service.js' />"></script>
<script src="<c:url value='/static/js/controller/ups_controller.js' />"></script>
<script src="<c:url value='/static/js/service/keyboard_service.js' />"></script>
<script src="<c:url value='/static/js/controller/keyboard_controller.js' />"></script>
<script src="<c:url value='/static/js/service/mouse_service.js' />"></script>
<script src="<c:url value='/static/js/controller/mouse_controller.js' />"></script>
<script src="<c:url value='/static/js/others/errors.js' />"></script>
</body>
</html>
