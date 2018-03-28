'use strict';

angular.module('myApp').controller('ComputerController', ['$scope', 'ComputerService', function($scope, ComputerService) {

    var self = this;
    self.hdd = {id:null,manufacturer:'',model:'',serial:'',capacity:'',hddType:''};
    self.motherboard={id:null,manufacturer:'',model:'',socket:''};
    self.powersupply={id:null,manufacturer:'',model:'',power:''};
    self.processor={id:null,manufacturer:'',model:'',speed:'',numberOfCores:''};
    self.ram={id:null,manufacturer:'',model:'',capacity:''};
    self.info = {id:null, invNumber:'', user:'', location:''};

    $scope.inner = ComputerService.getComputer();

    self.nextToInfo = nextToInfo;
    self.nextToHdd = nextToHdd;
    self.nextToMotherboard = nextToMotherboard;
    self.nextToProcessor = nextToProcessor;
    self.nextToPowerSupply = nextToPowerSupply;
    self.nextToRam = nextToRam;
    self.submit = submit;

    function nextToInfo() {
        window.location='#!computer/info'
    }

    function nextToHdd() {
        ComputerService.setInfo(self.info);
        window.location='#!computer/hdd'
    }

    function nextToMotherboard() {
        ComputerService.setHdd(self.hdd);
        window.location='#!computer/motherboard'
    }

    function nextToProcessor() {
        ComputerService.setMotherBoard(self.motherboard);
        window.location='#!computer/processor'
    }

    function nextToPowerSupply() {
        ComputerService.setProcessor(self.processor);
        window.location='#!computer/powersupply'
    }

    function nextToRam() {
        ComputerService.setPowerSupply(self.powersupply);
        window.location='#!computer/ram'
    }

    function submit() {
        ComputerService.setRam(self.ram);
        ComputerService.createComputer();
        alert("Внесение компьютера прошло успешно");
        window.location='/'
    }
}]);