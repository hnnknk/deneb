'use strict';

angular.module('myApp').factory('PowerSupplyService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/sysunit/powersupply/';
    var REST_SERVICE_URI_RO = 'http://localhost:8080/sysunit/ro/powersupply/';

    var factory = {
        fetchAllPowerSupplies: fetchAllPowerSupplies,
        fetchAllPowerSuppliesRO: fetchAllPowerSuppliesRO,
        createPowerSupply: createPowerSupply,
        updatePowerSupply:updatePowerSupply,
        deletePowerSupply:deletePowerSupply
    };

    return factory;

    function fetchAllPowerSupplies() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching PowerSupplies');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAllPowerSuppliesRO() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_RO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching PowerSupplies');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createPowerSupply(powersupply) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, powersupply)
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


    function updatePowerSupply(powersupply, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, powersupply)
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

    function deletePowerSupply(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting PowerSupply');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);