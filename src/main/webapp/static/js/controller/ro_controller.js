'use strict';

angular.module('myApp').controller('ROController', ['$scope', '$routeParams', 'MonitorService', 'UpsService', 'MouseService', 'KeyboardService', 'HddService',
    'RamService', 'ProcessorService', 'MotherBoardService', 'PowerSupplyService',
    function($scope, $routeParams, MonitorService, UpsService, MouseService, KeyboardService, HddService, RamService,
             ProcessorService, MotherBoardService, PowerSupplyService) {

    var self = this;
    self.monitor = {id:null, invNumber:'', manufacturer:'', model:'', serial:''};
    var monTitles = ['Инвентарный номер', 'Производитель', 'Модель', 'Серийный номер'];

    self.ups = {id:null, invNumber:'' ,manufacturer:'', model:'', serial:''};
    var uTitles = ['Инвентарный номер', 'Производитель', 'Модель', 'Серийный номер'];

    self.mouse = {id:null, invNumber:'', manufacturer:'', model:'', serial:''};
    var mTitles = ['Инвентарный номер', 'Производитель', 'Модель', 'Серийный номер'];

    self.keyboard = {id:null, invNumber:'', manufacturer:'', model:'', serial:''};
    var kTitles = ['Инвентарный номер', 'Производитель', 'Модель', 'Серийный номер'];

    self.hdd = {id:null, manufacturer:'', model:'', serial:'', capacity:'', hddtypes:''};
    var hTitles = ['Производитель', 'Модель', 'Серийный номер', 'Емкость', "Тип диска"];

    self.motherboard = {id:null, manufacturer:'', model:'', socket:''};
    var motTitles = ['Производитель', 'Модель', 'Сокет'];

    self.powersupply = {id:null, manufacturer:'', model:'', power:''};
    var powTitles = ['Производитель', 'Модель', 'Мощность'];

    self.processor = {id:null, manufacturer:'', model:'', speed:'', numberOfCores:''};
    var pTitles = ['Производитель', 'Модель', 'Скорость', 'Кол-во ядер'];

    self.ram = {id:null, manufacturer:'', model:'', capacity:''};
    var rTitles = ['Производитель', 'Модель', 'Емкость'];

    self.mainTitle = '';
    self.titles = [];
    self.items = [];

    setup();

    function setup() {
        switch ($routeParams.param) {
            case ('Mouse'):
                self.titles = mTitles;
                self.mainTitle = 'Список мышек';
                fetchAllMousesRO();
                break;
            case ('Keyboard'):
                self.titles = kTitles;
                self.mainTitle = 'Список клавиатур';
                fetchAllKeyboardsRO();
                break;
            case ('Monitor'):
                self.titles = monTitles;
                self.mainTitle = 'Список мониторов';
                fetchAllMonitorsRO();
                break;
            case ('Ups'):
                self.titles = uTitles;
                self.mainTitle = 'Список ИБП';
                fetchAllUpsesRO();
                break;
            case ('Hdd'):
                self.titles = hTitles;
                self.mainTitle = 'Список жестких дисков';
                fetchAllHddsRO();
                break;
            case ('Ram'):
                self.titles = rTitles;
                self.mainTitle = 'Список оперативной памяти';
                fetchAllRamsRO();
                break;
            case ('Processor'):
                self.titles = pTitles;
                self.mainTitle = 'Список процессоров';
                fetchAllProcessorsRO();
                break;
            case ('Motherboard'):
                self.titles = motTitles;
                self.mainTitle = 'Список материнских плат';
                fetchAllMotherBoardsRO();
                break;
            case ('Powersupply'):
                self.titles = powTitles;
                self.mainTitle = 'Список блоков питания';
                fetchAllPowerSuppliesRO();
                break;
            default:
                break;
        }
    }

    function fetchAllMonitorsRO(){
        MonitorService.fetchAllMonitorsRO()
            .then(
                function(d) {
                    self.items = d;
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
                    self.items = d;
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
                    self.items = d;
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
                    self.items = d;
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
                    self.items = d;
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
                    self.items = d;
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
                    self.items = d;
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
                    self.items = d;
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
                    self.items = d;
                },
                function(errResponse){
                    console.error('Error while fetching PowerSupplies');
                }
            );
    }

}]);