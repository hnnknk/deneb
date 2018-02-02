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


<h1>Login</h1>
<label>Username</label><input ng-model="data.username"/>
<label>Password</label><input type="password" ng-model="data.password"/>
<a href="#" ng-click="login()">Login</a>

<div id="user_div" class="generic-container" ng-controller="UserController as ctrl_user">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Регистрация</span></div>

        <div class="formcontainer">
            <form ng-submit="ctrl_user.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl_user.user.id" />
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Имя пользователя</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_user.user.name" name="user_name" class="field form-control input-sm" placeholder="Введите имя" required ng-minlength="1" ng-maxlength="7"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_invNumber.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.ups_invNumber.$error.minlength">Поле должно содержать как минимум 1 цифру</span>
                                <span ng-show="myForm.ups_invNumber.$error.maxlength">Поле должно содержать не более 7 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable">Пароль</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl_user.user.password" name="user_password" class="field form-control input-sm" placeholder="Введите пароль" required ng-minlength="2" ng-maxlength="10"/>
                            <div class="has-error" ng-show="myForm.$dirty">
                                <span ng-show="myForm.ups_manufacter.$error.required">Это поле необходимо заполнить</span>
                                <span ng-show="myForm.ups_manufacter.$error.minlength">Поле должно содержать как минимум 2 символа</span>
                                <span ng-show="myForm.ups_manufacter.$error.maxlength">Поле должно содержать не более 10 символов</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="Добавить" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
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
