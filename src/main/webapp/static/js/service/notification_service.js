'use strict';

angular.module('myApp').factory('NotificationService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/components/notification/';

    var factory = {
        fetchAllNotifications: fetchAllNotifications,
        updateNotification:updateNotification,
    };

    return factory;

    function fetchAllNotifications() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Notifications');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function updateNotification(notification, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, notification)
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

}]);