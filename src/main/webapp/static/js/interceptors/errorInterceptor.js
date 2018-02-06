angular.module('myApp')
    .factory('errorInterceptor', ['$q', function($q) {
        return {
            'responseError': function(rejection){

                var defer = $q.defer();

                if(rejection.status == 401){
                    console.dir(rejection);
                    console.log(rejection.config.url);
                    if(rejection.config.url.toString().includes("/components/monitor/")) {
                        window.location='/components#!roMonitor'
                    } else if(rejection.config.url.toString().includes("/components/ups/")) {
                        window.location='/components#!roUps'
                    } else if(rejection.config.url.toString().includes("/components/mouse/")) {
                        window.location='/components#!roMouse'
                    } else if(rejection.config.url.toString().includes("/components/keyboard/")) {
                        window.location='/components#!roKeyboard'
                    } else {
                        window.location='/error401'
                    }

                }

                defer.reject(rejection);

                return defer.promise;

            },
        };
    }]);