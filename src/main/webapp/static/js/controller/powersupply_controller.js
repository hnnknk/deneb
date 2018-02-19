'use strict';

angular.module('myApp').controller('PowerSupplyController', ['$scope', 'PowerSupplyService', function($scope, PowerSupplyService) {
    var self = this;
    self.powersupply={id:null,manufacter:'',model:'',power:''};
    self.powersupplies=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllPowersupplies();

    function fetchAllPowersupplies(){
        PowerSupplyService.fetchAllPowerSupplies()
            .then(
                function(d) {
                    self.powersupplies = d;
                },
                function(errResponse){
                    console.error('Error while fetching PowerSupplies');
                }
            );
    }

    function createPowersupply(powersupply){
        PowerSupplyService.createPowerSupply(powersupply)
            .then(
                fetchAllPowersupplies,
                function(errResponse){
                    console.error('Error while creating PowerSupply');
                }
            );
    }

    function updatePowersupply(powersupply, id){
        PowerSupplyService.updatePowerSupply(powersupply,id)
            .then(
                fetchAllPowersupplies,
                function(errResponse){
                    console.error('Error while updating PowerSupply');
                }
            );
    }

    function deletePowersupply(id){
        if (confirm('Вы действительно хотите удалить этот блок притания?')) {
            PowerSupplyService.deletePowerSupply(id)
                .then(
                    fetchAllPowersupplies,
                    function(errResponse){
                        console.error('Error while deleting PowerSupply');
                    }
                );
        }
    }

    function submit() {
        if(self.powersupply.id===null){
            $(".alert").removeClass("in").show();
            $(".alert").delay(1000).addClass("in").fadeOut(4000);
            console.log('Saving New Powersupply', self.powersupply);
            createPowersupply(self.powersupply);
        }else{
            updatePowersupply(self.powersupply, self.powersupply.id);
            console.log('Powersupply updated with id ', self.powersupply.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.powersupplies.length; i++){
            if(self.powersupplies[i].id === id) {
                self.powersupply = angular.copy(self.powersupplies[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.powersupply.id === id) {
            reset();
        }
        deletePowersupply(id);
    }


    function reset(){
        self.powersupply={id:null,manufacter:'',model:'',power:''};
        $scope.myForm.$setPristine();
    }

}]);