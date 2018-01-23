'use strict';

angular.module('myApp').factory('KeyboardService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/components/keyboard/';

    var factory = {
        fetchAllKeyboards: fetchAllKeyboards,
        createKeyboard: createKeyboard,
        updateKeyboard:updateKeyboard,
        deleteKeyboard:deleteKeyboard
    };

    return factory;

    function fetchAllKeyboards() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Keyboards');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createKeyboard(keyboard) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, keyboard)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while creating Keyboard');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateKeyboard(keyboard, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, keyboard)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while updating Keyboard');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteKeyboard(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while deleting Keyboard');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

}]);