'use strict';

angular.module('myApp').controller('mainCtrl', function($scope, $http, $httpParamSerializer, $cookies, $rootScope, UserService) {

    $scope.data = {
            grant_type:"password",
            username: "bill",
            password: "abc123",
            client_id: "my-trusted-client",
        };
        $scope.encoded = btoa("my-trusted-client:secret");

        $scope.login = function() {
            console.log("tttttt");
            console.log($scope.data);

            var req = {
                method: 'POST',
                url: "http://localhost:8080/oauth/token",
                headers: {
                    "Authorization": "Basic " + $scope.encoded,
                    "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data: $httpParamSerializer($scope.data)

            }


            $http(req)
                .then(
                    function(data){
                $http.defaults.headers.common.Authorization =
                    'Bearer ' + data.data.access_token;
                console.log($http.defaults.headers.common.Authorization)
                $cookies.put("access_token", data.data.access_token);

                UserService.setS(true);
                $rootScope.$broadcast('login-done');
                window.location.href='#';
                    }, function (error) {
                        console.error(error);
                        handleErrors(error.status)
                    }
                );
        }

    });