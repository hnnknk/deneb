'use strict';

angular.module('myApp').controller('NotificationController', ['$scope', 'NotificationService', function($scope, NotificationService) {
    var self = this;
    self.notification={id:null,monitorCreated:'',upsCreated:'',mouseCreated:'',keyboardCreated:'',userCreated:''}
    self.notifications=[];

    self.submit = submit;

    fetchAllNotifications();

    function fetchAllNotifications(){
        NotificationService.fetchAllNotifications()
            .then(
                function(d) {
                    self.notifications = d;
                    for(var i = 0; i < self.notifications.length; i++){
                        self.notification = angular.copy(self.notifications[i]);
                        console.log(self.notification);
                        break;
                    }
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
    }

}]);