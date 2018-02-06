'use strict';

angular.module('myApp').controller('ROController', ['$scope', 'MonitorService', 'UpsService', 'MouseService', 'KeyboardService', function($scope, MonitorService, UpsService, MouseService, KeyboardService) {
    var self = this;
    self.monitor={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.monitors=[];

    self.ups={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.upses=[];

    self.mouse={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.mouses=[];

    self.keyboard={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.keyboards=[];

    fetchAllMonitorsRO();
    fetchAllUpsesRO();
    fetchAllMousesRO();
    fetchAllKeyboardsRO();


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
}]);