'use strict';

angular.module('myApp').factory('UpsService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/components/ups/';
    var REST_SERVICE_URI_RO = 'http://localhost:8080/components/ro/ups/';

    var factory = {
        fetchAllUpses: fetchAllUpses,
        fetchAllUpsesRO: fetchAllUpsesRO,
        createUps: createUps,
        updateUps:updateUps,
        deleteUps:deleteUps
    };

    return factory;

    function fetchAllUpses() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Upses');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAllUpsesRO() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_RO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Upses');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createUps(monitor) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, monitor)
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


    function updateUps(monitor, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, monitor)
            .then(
                function (response) {
                    handleSuccessUpdate()
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    handleErrors(errResponse.status);
                }
            );
        return deferred.promise;
    }

    function deleteUps(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Ups');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);