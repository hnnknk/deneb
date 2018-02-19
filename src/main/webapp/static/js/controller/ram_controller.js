'use strict';

angular.module('myApp').controller('RamController', ['$scope', 'RamService', function($scope, RamService) {
    var self = this;
    self.ram={id:null,manufacter:'',model:'',capacity:''};
    self.rams=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllRams();

    function fetchAllRams(){
        RamService.fetchAllRams()
            .then(
                function(d) {
                    self.rams = d;
                },
                function(errResponse){
                    console.error('Error while fetching Rams');
                }
            );
    }

    function createRam(ram){
        RamService.createRam(ram)
            .then(
                fetchAllRams,
                function(errResponse){
                    console.error('Error while creating Ram');
                }
            );
    }

    function updateRam(ram, id){
        RamService.updateRam(ram,id)
            .then(
                fetchAllRams,
                function(errResponse){
                    console.error('Error while updating Ram');
                }
            );
    }

    function deleteRam(id){
        if (confirm('Вы действительно хотите удалить эту оперативную память?')) {
            RamService.deleteRam(id)
                .then(
                    fetchAllRams,
                    function(errResponse){
                        console.error('Error while deleting Ram');
                    }
                );
        }
    }

    function submit() {
        if(self.ram.id===null){
            $(".alert").removeClass("in").show();
            $(".alert").delay(1000).addClass("in").fadeOut(4000);
            console.log('Saving New Ram', self.ram);
            console.log('ram type =',self.ram.ramtype)
            createRam(self.ram);
        }else{
            updateRam(self.ram, self.ram.id);
            console.log('Ram updated with id ', self.ram.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.rams.length; i++){
            if(self.rams[i].id === id) {
                self.ram = angular.copy(self.rams[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.ram.id === id) {
            reset();
        }
        deleteRam(id);
    }


    function reset(){
        self.ram={id:null,manufacter:'',model:'',capacity:''};
        $scope.myForm.$setPristine();
    }

}]);