'use strict';

angular.module('myApp').controller('ROController', ['$scope', 'MonitorService', 'UpsService', 'MouseService', 'KeyboardService', 'HddService',
    function($scope, MonitorService, UpsService, MouseService, KeyboardService, HddService) {

    var self = this;
    self.monitor={id:null,invNumber:'',manufacter:'',model:'',serial:''};
    self.monitors=[];

    self.ups={id:null,invNumber:'',manufacter:'',model:'',serial:''};
    self.upses=[];

    self.mouse={id:null,invNumber:'',manufacter:'',model:'',serial:''};
    self.mouses=[];

    self.keyboard={id:null,invNumber:'',manufacter:'',model:'',serial:''};
    self.keyboards=[];

    self.hdd={id:null,manufacter:'',model:'',serial:'',capacity:'',hddtypes:''};
    self.hdds=[];

    fetchAllMonitorsRO();
    fetchAllUpsesRO();
    fetchAllMousesRO();
    fetchAllKeyboardsRO();
    fetchAllHddsRO();


    function fetchAllMonitorsRO(){
        MonitorService.fetchAllMonitorsRO()
            .then(
                function(d) {
                    self.monitors = d;
                },
                function(errResponse){
                    console.error('Error while fetching Monitors');
                }
            );
    }

    function fetchAllUpsesRO(){
        UpsService.fetchAllUpsesRO()
            .then(
                function(d) {
                    self.upses = d;
                },
                function(errResponse){
                    console.error('Error while fetching Upses');
                }
            );
    }

    function fetchAllMousesRO(){
        MouseService.fetchAllMousesRO()
            .then(
                function(d) {
                    self.mouses = d;
                },
                function(errResponse){
                    console.error('Error while fetching Mouses');
                }
            );
    }

    function fetchAllKeyboardsRO(){
        KeyboardService.fetchAllKeyboardsRO()
            .then(
                function(d) {
                    self.keyboards = d;
                },
                function(errResponse){
                    console.error('Error while fetching Keyboards');
                }
            );
    }

    function fetchAllHddsRO(){
        HddService.fetchAllHddsRO()
            .then(
                function(d) {
                    self.hdds = d;
                },
                function(errResponse){
                    console.error('Error while fetching Hdds');
                }
            );
    }
}]);