'use strict';

angular.module('myApp').controller('MonitorController', ['$scope', 'MonitorService', function($scope, MonitorService) {
    var self = this;
    self.monitor={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.monitors=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllMonitors();

    function fetchAllMonitors(){
        MonitorService.fetchAllMonitors()
            .then(
                function(d) {
                    self.monitors = d;
                },
                function(errResponse){
                    console.error('Error while fetching Monitors');
                }
            );
    }

    function createMonitor(monitor){
        MonitorService.createMonitor(monitor)
            .then(
                fetchAllMonitors,
                function(errResponse){
                    console.error('Error while creating Monitor');
                }
            );
    }

    function updateMonitor(monitor, id){
        MonitorService.updateMonitor(monitor,id)
            .then(
                fetchAllMonitors,
                function(errResponse){
                    console.error('Error while updating Monitor');
                }
            );
    }

    function deleteMonitor(id){
        if (confirm('Вы действительно хотите удалить этот монитор?')) {
            MonitorService.deleteMonitor(id)
                .then(
                    fetchAllMonitors,
                    function(errResponse){
                        console.error('Error while deleting Monitor');
                    }
                );
        }
    }

    function submit() {
        if(self.monitor.id===null){
            console.log('Saving New Monitor', self.monitor);
            createMonitor(self.monitor);
        }else{
            updateMonitor(self.monitor, self.monitor.id);
            console.log('Monitor updated with id ', self.monitor.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.monitors.length; i++){
            if(self.monitors[i].id === id) {
                self.monitor = angular.copy(self.monitors[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.monitor.id === id) {
            reset();
        }
        deleteMonitor(id);
    }


    function reset(){
        self.monitor={id:null,invNumber:'',manufacter:'',model:'',serial:''};
        $scope.myForm.$setPristine();
    }

    function monitorExists() {
        fetchAllMonitors;
         $(".alert").removeClass("in").show();
            $(".alert").delay(1000).addClass("in").fadeOut(4000);
    }

}]);