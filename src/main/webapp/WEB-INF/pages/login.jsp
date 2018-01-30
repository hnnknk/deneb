<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Страница регистрации</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/error.css' />" rel="stylesheet"/>

</head>
<body ng-app="myApp" ng-controller="mainCtrl">

<div class="badalert alert-danger fade al">
    <strong>Не удается войти. Пожалуйста, проверьте правильность написания логина и пароля.</strong>
</div>


<h1>Login</h1>
<label>Username</label><input ng-model="data.username"/>
<label>Password</label><input type="password" ng-model="data.password"/>
<a href="#" ng-click="login()">Login</a>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script
        src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-resource.min.js">
</script>
<script
        src="http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular-route.min.js">
</script>
<script
        src="https://code.angularjs.org/1.4.4/angular-cookies.min.js">
</script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/ngStorage/0.3.9/ngStorage.min.js">
</script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/interceptors/errorInterceptor.js' />"></script>
<script src="<c:url value='/static/js/interceptors/headerInterceptor.js' />"></script>
<script src="<c:url value='/static/js/controller/oauth_controller.js' />"></script>
<script src="<c:url value='/static/js/others/errors.js' />"></script>
</body>
</html>
