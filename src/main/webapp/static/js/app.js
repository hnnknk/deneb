'use strict';

var App = angular.module('myApp',['ngCookies'])


App.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('headerInterceptor','errorInterceptor');
}]);