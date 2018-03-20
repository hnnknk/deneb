'use strict';

angular.module('myApp').controller('HddController', ['$scope', 'HddService', function($scope, HddService) {
    var self = this;
    self.hdd={id:null,manufacturer:'',model:'',serial:'',capacity:'',hddType:''};
    self.hdds=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllHdds();

    function fetchAllHdds(){
        HddService.fetchAllHdds()
            .then(
                function(d) {
                    self.hdds = d;
                },
                function(errResponse){
                    console.error('Error while fetching Hdds');
                }
            );
    }

    function createHdd(hdd){
        HddService.createHdd(hdd)
            .then(
                fetchAllHdds,
                function(errResponse){
                    console.error('Error while creating Hdd');
                }
            );
    }

    function updateHdd(hdd, id){
        HddService.updateHdd(hdd,id)
            .then(
                fetchAllHdds,
                function(errResponse){
                    console.error('Error while updating Hdd');
                }
            );
    }

    function deleteHdd(id){
        if (confirm('Вы действительно хотите удалить этот жесткий диск?')) {
            HddService.deleteHdd(id)
                .then(
                    fetchAllHdds,
                    function(errResponse){
                        console.error('Error while deleting Hdd');
                    }
                );
        }
    }

    function submit() {
        if(self.hdd.id===null){
            $(".alert").removeClass("in").show();
            $(".alert").delay(1000).addClass("in").fadeOut(4000);
            console.log('Saving New Hdd', self.hdd);
            console.log('hdd type =',self.hdd.hddtype)
            createHdd(self.hdd);
        }else{
            updateHdd(self.hdd, self.hdd.id);
            console.log('Hdd updated with id ', self.hdd.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.hdds.length; i++){
            if(self.hdds[i].id === id) {
                self.hdd = angular.copy(self.hdds[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.hdd.id === id) {
            reset();
        }
        deleteHdd(id);
    }


    function reset(){
        self.hdd={id:null,manufacturer:'',model:'',serial:'',capacity:'',hddType:''};
        $scope.myForm.$setPristine();
    }

}]);