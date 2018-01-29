<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AngularJS $http Example</title>
</head>
<body ng-app="myApp" ng-controller="mainCtrl">
<h1>Login</h1>
<label>Username</label><input ng-model="data.username"/>
<label>Password</label><input type="password" ng-model="data.password"/>
<a href="#" ng-click="login()">Login</a>

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
<script src="<c:url value='/static/js/controller/oauth_controller.js' />"></script>
</body>
</html>
