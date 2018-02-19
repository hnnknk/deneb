'use strict';

var App = angular.module('myApp',['ngCookies','ngRoute','ngResource']);


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
        .when('/notification',{

            templateUrl: 'static/views/notification.html'

        })
        .when('/hdd',{

            templateUrl: 'static/views/hdd.html'

        })
        .when('/ram',{

            templateUrl: 'static/views/ram.html'

        })
        .when('/motherboard',{

            templateUrl: 'static/views/motherboard.html'

        })
        .when('/powersupply',{

            templateUrl: 'static/views/powersupply.html'

        })
        .when('/processor',{

            templateUrl: 'static/views/processor.html'

        })
        .when('/roHdd',{

            templateUrl: 'static/views/roHdd.html'

        })
        .when('/roMonitor',{

            templateUrl: 'static/views/roMonitor.html'

        })
        .when('/roUps',{

            templateUrl: 'static/views/roUps.html'

        })
        .when('/roMouse',{

            templateUrl: 'static/views/roMouse.html'

        })
        .when('/roKeyboard',{

            templateUrl: 'static/views/roKeyboard.html'

        })
        .when('/roRam',{

            templateUrl: 'static/views/roRam.html'

        })
        .when('/roProcessor',{

            templateUrl: 'static/views/roProcessor.html'

        })
        .when('/roMotherboard',{

            templateUrl: 'static/views/roMotherboard.html'

        })
        .when('/roPowersupply',{

            templateUrl: 'static/views/roPowersupply.html'

        })
        .when('/login',{

            templateUrl: 'static/views/login.html'

        })
        .otherwise(

            { redirectTo: '/'}
        );

});
