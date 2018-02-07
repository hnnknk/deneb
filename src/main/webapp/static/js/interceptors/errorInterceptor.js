angular.module('myApp')
    .factory('errorInterceptor', ['$q', function($q) {
        return {
            'responseError': function(rejection){

                var defer = $q.defer();

                if(rejection.status === 401){
                    window.location='/error401'
                }

                defer.reject(rejection);

                return defer.promise;

            }
        };
    }]);