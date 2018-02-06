'use strict';

angular.module('myApp').factory('MonitorService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/components/monitor/';
    var REST_SERVICE_URI_RO = 'http://localhost:8080/components/ro/monitor/';

    var factory = {
        fetchAllMonitors: fetchAllMonitors,
        fetchAllMonitorsRO: fetchAllMonitorsRO,
        createMonitor: createMonitor,
        updateMonitor:updateMonitor,
        deleteMonitor:deleteMonitor
    };

    return factory;

    function fetchAllMonitors() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI).then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Monitors');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function fetchAllMonitorsRO() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI_RO).then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Monitors');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createMonitor(monitor) {
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


    function updateMonitor(monitor, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, monitor)
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

    function deleteMonitor(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Monitor');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);