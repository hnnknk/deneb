'use strict';

var App = angular.module('myApp',['ngCookies','ngRoute','ngResource'])


App.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('headerInterceptor','errorInterceptor');
}]);

App.config(function($routeProvider){
    $routeProvider
        .when('/monitor',{
            templateUrl: 'static/views/monitor.html'

        })
        .when('/ups',{

            templateUrl: 'static/views/ups.html'

        })
        .when('/mouse',{

            templateUrl: 'static/views/mouse.html'

        })
        .when('/keyboard',{

            templateUrl: 'static/views/keyboard.html'

        })
        .otherwise(

            { redirectTo: '/'}
        );

});
