'use strict';

angular.module('myApp').factory('ComputerService', ['$http', '$q', function($http, $q){

    var inner = {};
    var REST_SERVICE_URI = 'http://localhost:8080/computers/';

    var factory = {
        setInfo: setInfo,
        setHdd: setHdd,
        getComputer: getComputer
    };


    return factory;

    function setInfo(info) {
        inner = info;
        console.log(inner);
    }

    function setHdd(hdd) {
        inner.hdd = hdd;
        createComputer(getComputer());
    }

    function getComputer() {
        return inner;
    }

    function createComputer(computer) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, computer)
            .then(
                function (response) {
                    handleSuccessCreate();
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    handleErrors(errResponse.status);
                }
            );
        return deferred.promise;
    }

}]);