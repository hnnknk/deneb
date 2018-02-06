angular.module('myApp')
    .factory('errorInterceptor', ['$q', function($q) {
        return {
            'responseError': function(rejection){

                var defer = $q.defer();

                if(rejection.status == 401){
                    console.dir(rejection);
                    console.log(rejection.config.url);
                    if(rejection.config.url.toString().includes("/components/monitor/")) {
                        window.location.href='#!roMonitor'
                    } else if(rejection.config.url.toString().includes("/components/ups/")) {
                        window.location.href='#!roUps'
                    } else if(rejection.config.url.toString().includes("/components/mouse/")) {
                        window.location.href='#!roMouse'
                    } else if(rejection.config.url.toString().includes("/components/keyboard/")) {
                        window.location.href='#!roKeyboard'
                    } else {
                        window.location='/error401'
                    }

                }

                defer.reject(rejection);

                return defer.promise;

            },
        };
    }]);