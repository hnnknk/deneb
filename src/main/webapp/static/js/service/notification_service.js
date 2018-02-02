'use strict';

angular.module('myApp').factory('NotificationService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/components/notification';

    var factory = {
        getNotification: getNotification,
        updateNotification: updateNotification
    };

    return factory;


    function getNotification() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Notification');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateNotification(notification) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI, notification)
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

}]);