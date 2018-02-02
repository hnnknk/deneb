'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,name:'',password:''};
    self.users=[];

    self.submit = submit;


    function createUser(user){
        UserService.createUser(user)
            .then(
                function (value) {
                    console.log('Successful created')
                },
                function(errResponse){
                    console.error('Error while creating User');
                }
            );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }
        reset();
    }

    function reset(){
        self.user={id:null,name:'',password:''};
        $scope.myForm.$setPristine();
    }

}]);