'use strict';

var App = angular.module('myApp',['ngCookies'])

App.config(['$httpProvider', function($httpProvider) {

    $httpProvider.interceptors.push(function($q) {

        return {

            'responseError': function(rejection){

                var defer = $q.defer();

                if(rejection.status == 401){
                    console.dir(rejection);
                    window.location='/t'
                }

                defer.reject(rejection);

                return defer.promise;

            }
        };
    });

}]);