'use strict';

angular.module('myApp').controller('navCtrl', function($scope, $http, $httpParamSerializer, $cookies, $rootScope, UserService) {

    $scope.islogged = UserService.getS();

    var prep = {
        method:'POST',
        url: 'http://localhost:8080/oauth/check_token?token=' + $cookies.get("access_token"),
    };

    check();

    function check() {
        $http(prep)
            .then(function (value) {
                if(value.status === 200) {
                    console.log("token is ok");
                    UserService.setS(true);
                    $rootScope.$broadcast('login-done');
                }
            }, function (error) {
                UserService.setS(false);
                console.log("bad token")
            });
    }



    $scope.logout = function() {
        $cookies.remove("access_token");
        window.location.href='/';
    };

    $rootScope.$on("login-done", function() {
        $scope.islogged = UserService.getS();
    });


});