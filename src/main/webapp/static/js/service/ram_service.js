'use strict';

angular.module('myApp').factory('RamService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/sysunit/ram/';
    var REST_SERVICE_URI_RO = 'http://localhost:8080/sysunit/ro/ram/';

    var factory = {
        fetchAllRams: fetchAllRams,
        fetchAllRamsRO: fetchAllRamsRO,
        createRam: createRam,
        updateRam:updateRam,
        deleteRam:deleteRam
    };

    return factory;

    function fetchAllRams() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Rams');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAllRamsRO() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_RO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Rams');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createRam(ram) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, ram)
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


    function updateRam(ram, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, ram)
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

    function deleteRam(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Ram');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);