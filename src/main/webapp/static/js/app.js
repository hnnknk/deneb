'use strict';

var App = angular.module('myApp',['ngCookies','ngRoute','ngResource', 'ngAnimate']);


App.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('headerInterceptor','errorInterceptor');
}]);

App.config(function($routeProvider){
    $routeProvider
        .when('/ro:param',{

            templateUrl: 'static/views/ro.html'

        })
        .when('/computer',{

            templateUrl: 'static/views/computer.html',
            controller: 'ComputerController'

        })
        .when('/computer/:state',{

            templateUrl: function(params){ return 'static/views/computer-' + params.state + '.html'; },
            controller: 'ComputerController'

        })
        .when('/:path',{

            templateUrl: function(params){ return 'static/views/' + params.path + '.html'; }

        })
        .otherwise(

            { redirectTo: '/'}

        );

});
