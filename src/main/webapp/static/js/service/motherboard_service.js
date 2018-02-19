'use strict';

angular.module('myApp').factory('MotherBoardService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/sysunit/motherboard/';
    var REST_SERVICE_URI_RO = 'http://localhost:8080/sysunit/ro/motherboard/';

    var factory = {
        fetchAllMotherboards: fetchAllMotherboards,
        fetchAllMotherboardsRO: fetchAllMotherboardsRO,
        createMotherboard: createMotherboard,
        updateMotherboard:updateMotherboard,
        deleteMotherboard:deleteMotherboard
    };

    return factory;

    function fetchAllMotherboards() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Motherboards');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAllMotherboardsRO() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_RO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Motherboards');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createMotherboard(motherboard) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, motherboard)
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


    function updateMotherboard(motherboard, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, motherboard)
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

    function deleteMotherboard(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Motherboard');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);