'use strict';

angular.module('myApp').controller('MotherBoardController', ['$scope', 'MotherBoardService', function($scope, MotherBoardService) {
    var self = this;
    self.motherboard={id:null,manufacturer:'',model:'',socket:''};
    self.motherboards=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllMotherboards();

    function fetchAllMotherboards(){
        MotherBoardService.fetchAllMotherboards()
            .then(
                function(d) {
                    self.motherboards = d;
                },
                function(errResponse){
                    console.error('Error while fetching Motherboards');
                }
            );
    }

    function createMotherboard(motherboard){
        MotherBoardService.createMotherboard(motherboard)
            .then(
                fetchAllMotherboards,
                function(errResponse){
                    console.error('Error while creating Motherboard');
                }
            );
    }

    function updateMotherboard(motherboard, id){
        MotherBoardService.updateMotherboard(motherboard,id)
            .then(
                fetchAllMotherboards,
                function(errResponse){
                    console.error('Error while updating Motherboard');
                }
            );
    }

    function deleteMotherboard(id){
        if (confirm('Вы действительно хотите удалить эту материнскую плату?')) {
            MotherBoardService.deleteMotherboard(id)
                .then(
                    fetchAllMotherboards,
                    function(errResponse){
                        console.error('Error while deleting Motherboard');
                    }
                );
        }
    }

    function submit() {
        if(self.motherboard.id===null){
            $(".alert").removeClass("in").show();
            $(".alert").delay(1000).addClass("in").fadeOut(4000);
            console.log('Saving New Motherboard', self.motherboard);
            createMotherboard(self.motherboard);
        }else{
            updateMotherboard(self.motherboard, self.motherboard.id);
            console.log('Motherboard updated with id ', self.motherboard.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.motherboards.length; i++){
            if(self.motherboards[i].id === id) {
                self.motherboard = angular.copy(self.motherboards[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.motherboard.id === id) {
            reset();
        }
        deleteMotherboard(id);
    }


    function reset(){
        self.motherboard={id:null,manufacturer:'',model:'',socket:''};
        $scope.myForm.$setPristine();
    }

}]);