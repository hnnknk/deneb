'use strict';

angular.module('myApp').controller('NotificationController', ['$scope', 'NotificationService', function($scope, NotificationService) {
    var self = this;
    self.notification={id:null,email:'',monitorCreated:'',upsCreated:'',mouseCreated:'',keyboardCreated:'',userCreated:''};
    self.notifications=[];

    self.submit = submit;

    fetchAllNotifications();

    function fetchAllNotifications(){
        NotificationService.fetchAllNotifications()
            .then(
                function(d) {
                    self.notifications = d;
                    self.notification = angular.copy(self.notifications[0]);
                },
                function(errResponse){
                    console.error('Error while fetching Notifications');
                }
            );
    }

    function updateNotification(notification, id){
        NotificationService.updateNotification(notification,id)
            .then(
                fetchAllNotifications,
                function(errResponse){
                    console.error('Error while updating Notification');
                }
            );
    }

    function submit() {
        updateNotification(self.notification, self.notification.id);
        console.log('Notification updated with id ', self.notification.id);
        $scope.myForm.$setPristine();
    }

}]);