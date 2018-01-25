'use strict';

angular.module('myApp').factory('MouseService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/components/mouse/';

    var factory = {
        fetchAllMouses: fetchAllMouses,
        createMouse: createMouse,
        updateMouse:updateMouse,
        deleteMouse:deleteMouse
    };

    return factory;

    function fetchAllMouses() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Mouses');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createMouse(mouse) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, mouse)
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


    function updateMouse(mouse, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, mouse)
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

    function deleteMouse(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Mouse');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);