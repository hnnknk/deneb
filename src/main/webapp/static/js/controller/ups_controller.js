'use strict';

angular.module('myApp').controller('UpsController', ['$scope', 'UpsService', function($scope, UpsService) {
    var self = this;
    self.ups={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.upses=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllUpses();

    function fetchAllUpses(){
        UpsService.fetchAllUpses()
            .then(
                function(d) {
                    self.upses = d;
                },
                function(errResponse){
                    console.error('Error while fetching Upses');
                }
            );
    }

    function createUps(ups){
        UpsService.createUps(ups)
            .then(
                fetchAllUpses,
                function(errResponse){
                    console.error('Error while creating Ups');
                }
            );
    }

    function updateUps(ups, id){
        UpsService.updateUps(ups,id)
            .then(
                fetchAllUpses,
                function(errResponse){
                    console.error('Error while updating Ups');
                }
            );
    }

    function deleteUps(id){
        UpsService.deleteUps(id)
            .then(
                fetchAllUpses,
                function(errResponse){
                    console.error('Error while deleting Ups');
                }
            );
    }

    function submit() {
        if(self.ups.id===null){
            console.log('Saving New Ups', self.ups);
            createUps(self.ups);
        }else{
            updateUps(self.ups, self.ups.id);
            console.log('Ups updated with id ', self.ups.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.upses.length; i++){
            if(self.upses[i].id === id) {
                self.ups = angular.copy(self.upses[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.ups.id === id) {
            reset();
        }
        deleteUps(id);
    }


    function reset(){
        self.ups={id:null,invNumber:'',manufacter:'',model:'',serial:''};
        $scope.myForm.$setPristine();
    }

}]);