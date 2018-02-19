'use strict';

angular.module('myApp').factory('ProcessorService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/sysunit/processor/';
    var REST_SERVICE_URI_RO = 'http://localhost:8080/sysunit/ro/processor/';

    var factory = {
        fetchAllProcessors: fetchAllProcessors,
        fetchAllProcessorsRO: fetchAllProcessorsRO,
        createProcessor: createProcessor,
        updateProcessor:updateProcessor,
        deleteProcessor:deleteProcessor
    };

    return factory;

    function fetchAllProcessors() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Processors');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAllProcessorsRO() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_RO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Processors');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createProcessor(processor) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, processor)
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


    function updateProcessor(processor, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, processor)
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

    function deleteProcessor(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Processor');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);