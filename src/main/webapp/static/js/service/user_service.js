'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/user/';

    var s = false;

    var factory = {
        createUser: createUser,
        getS: getS,
        setS: setS
    };

    return factory;


    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating User');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getS() {
        return s;
    }

    function setS(e) {
        s = e;
    }

}]);