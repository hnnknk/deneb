'use strict';

angular.module('myApp').controller('ROController', ['$scope', 'MonitorService', 'UpsService', 'MouseService', 'KeyboardService', 'HddService',
    'RamService', 'ProcessorService', 'MotherBoardService', 'PowerSupplyService',
    function($scope, MonitorService, UpsService, MouseService, KeyboardService, HddService, RamService,
             ProcessorService, MotherBoardService, PowerSupplyService) {

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

    self.motherboard={id:null,manufacter:'',model:'',socket:''};
    self.motherboards=[];

    self.powersupply={id:null,manufacter:'',model:'',power:''};
    self.powersupplies=[];

    self.processor={id:null,manufacter:'',model:'',speed:'',numberOfCores:''};
    self.processors=[];

    self.ram={id:null,manufacter:'',model:'',capacity:''};
    self.rams=[];

    fetchAllMonitorsRO();
    fetchAllUpsesRO();
    fetchAllMousesRO();
    fetchAllKeyboardsRO();
    fetchAllHddsRO();

    fetchAllRamsRO();
    fetchAllProcessorsRO();
    fetchAllPowerSuppliesRO();
    fetchAllMotherBoardsRO();


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

    function fetchAllRamsRO(){
        RamService.fetchAllRamsRO()
            .then(
                function(d) {
                    self.rams = d;
                },
                function(errResponse){
                    console.error('Error while fetching Rams');
                }
            );
    }

    function fetchAllProcessorsRO(){
        ProcessorService.fetchAllProcessorsRO()
            .then(
                function(d) {
                    self.processors = d;
                },
                function(errResponse){
                    console.error('Error while fetching Processors');
                }
            );
    }

    function fetchAllMotherBoardsRO(){
        MotherBoardService.fetchAllMotherboardsRO()
            .then(
                function(d) {
                    self.motherboards = d;
                },
                function(errResponse){
                    console.error('Error while fetching MotherBoards');
                }
            );
    }

    function fetchAllPowerSuppliesRO(){
        PowerSupplyService.fetchAllPowerSuppliesRO()
            .then(
                function(d) {
                    self.powersupplies = d;
                },
                function(errResponse){
                    console.error('Error while fetching PowerSupplies');
                }
            );
    }

}]);