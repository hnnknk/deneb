'use strict';

angular.module('myApp').factory('HddService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/sysunit/hdd/';
    var REST_SERVICE_URI_RO = 'http://localhost:8080/sysunit/ro/hdd/';

    var factory = {
        fetchAllHdds: fetchAllHdds,
        fetchAllHddsRO: fetchAllHddsRO,
        createHdd: createHdd,
        updateHdd:updateHdd,
        deleteHdd:deleteHdd
    };

    return factory;

    function fetchAllHdds() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Hdds');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAllHddsRO() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_RO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Hdds');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createHdd(hdd) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, hdd)
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


    function updateHdd(hdd, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, hdd)
            .then(
                function (response) {
                    handleSuccessUpdate();
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    handleErrors(errResponse.status);
                }
            );
        return deferred.promise;
    }

    function deleteHdd(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Hdd');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);