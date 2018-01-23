'use strict';

angular.module('myApp').controller('MouseController', ['$scope', 'MouseService', function($scope, MouseService) {
    var self = this;
    self.mouse={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.mouses=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllMouses();

    function fetchAllMouses(){
        MouseService.fetchAllMouses()
            .then(
                function(d) {
                    self.mouses = d;
                },
                function(errResponse){
                    console.error('Error while fetching Mouses');
                }
            );
    }

    function createMouse(mouse){
        MouseService.createMouse(mouse)
            .then(
                fetchAllMouses,
                function(errResponse){
                    console.error('Error while creating Mouse');
                }
            );
    }

    function updateMouse(mouse, id){
        MouseService.updateMouse(mouse,id)
            .then(
                fetchAllMouses,
                function(errResponse){
                    console.error('Error while updating Mouse');
                }
            );
    }

    function deleteMouse(id){
        if (confirm('Вы действительно хотите удалить эту мышку?')) {
            MouseService.deleteMouse(id)
                .then(
                    fetchAllMouses,
                    function(errResponse){
                        console.error('Error while deleting Mouse');
                    }
                );
        }
    }

    function submit() {
        if(self.mouse.id===null){
            console.log('Saving New Mouse', self.mouse);
            createMouse(self.mouse);
        }else{
            updateMouse(self.mouse, self.mouse.id);
            console.log('Mouse updated with id ', self.mouse.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.mouses.length; i++){
            if(self.mouses[i].id === id) {
                self.mouse = angular.copy(self.mouses[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.mouse.id === id) {
            reset();
        }
        deleteMouse(id);
    }


    function reset(){
        self.mouse={id:null,invNumber:'',manufacter:'',model:'',serial:''};
        $scope.myForm.$setPristine();
    }

}]);