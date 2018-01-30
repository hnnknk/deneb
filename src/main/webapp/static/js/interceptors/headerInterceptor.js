angular.module('myApp')
    .factory('headerInterceptor', ['$cookies', function($cookies) {
        return {

            'request': function(config) {

                if(!window.location.href.toString().includes("login")) {
                    config.headers = config.headers || {};
                    config.headers.Authorization = 'Bearer ' + $cookies.get("access_token");
                }

                return config;
            }
        };
    }]);