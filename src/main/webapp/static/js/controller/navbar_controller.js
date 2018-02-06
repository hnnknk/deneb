'use strict';

angular.module('myApp').controller('navCtrl', function($scope, $http, $httpParamSerializer, $cookies, $rootScope, UserService) {

    $scope.islogged = UserService.getS();

    $scope.logout = function() {
        $cookies.remove("access_token");
        window.location.href='/';
    }

    $rootScope.$on("login-done", function() {
        $scope.islogged = UserService.getS();
    });


});