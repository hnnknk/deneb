<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Страница регистрации</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/error.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>

</head>
<body ng-app="myApp" ng-controller="mainCtrl">

<div class="badalert alert-danger fade al">
    <strong>Не удается войти. Пожалуйста, проверьте правильность написания логина и пароля.</strong>
</div>

<div id="login_div" class="generic-container">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Вход в систему</span></div>

        <div class="formcontainer">
            <form ng-submit="login()" name="myForm" class="form-horizontal">
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Имя пользователя</label>
                        <div class="col-md-6">
                            <input type="text" ng-model="data.username" name="username" class="field form-control input-sm" placeholder="Введите имя пользователя"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Пароль</label>
                        <div class="col-md-6">
                            <input type="password" ng-model="data.password" name="password" class="field form-control input-sm" placeholder="Введите пароль"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="Войти" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

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
<script src="<c:url value='/static/js/service/user_service.js' />"></script>
<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
<script src="<c:url value='/static/js/others/errors.js' />"></script>
</body>
</html>
