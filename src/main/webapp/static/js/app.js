'use strict';

var App = angular.module('myApp',['ngCookies','ngRoute','ngResource']);


App.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('headerInterceptor','errorInterceptor');
}]);

App.config(function($routeProvider){
    $routeProvider
        .when('/ro:param',{

            templateUrl: 'static/views/ro.html'

        })
        .when('/:path',{

            templateUrl: function(params){ return 'static/views/' + params.path + '.html'; }

        })
        .otherwise(

            { redirectTo: '/'}

        );

});
