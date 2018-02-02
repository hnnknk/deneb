'use strict';

angular.module('myApp').controller('NotificationController', ['$scope', 'NotificationService', function($scope, NotificationService) {
    var self = this;
    self.Notification={id:null,isMonitor:'',isMouse:'',isKeyboard:'',isUps:'',isUser:''};
    self.Notifications=[];

    self.submit = submit;
    self.edit = edit;
    self.reset = reset;


    getNotification();

    function getNotification(){
        NotificationService.getNotification()
            .then(
                function(d) {
                    self.notifications = d;
                },
                function(errResponse){
                    console.error('Error while fetching Notification');
                }
            );
    }

    function updateNotification(notification){
        NotificationService.updateNotification(notification)
            .then(
                getNotification,
                function(errResponse){
                    console.error('Error while updating Notification');
                }
            );
    }


    function submit() {
        updateNotification(self.Notification);
        console.log('Notificationupdated with id ', self.Notification.id);
        reset();
    }


    function reset(){
        self.Notification={id:null,isMonitor:'',isMouse:'',isKeyboard:'',isUps:'',isUser:''};
        $scope.myForm.$setPristine();
    }

}]);