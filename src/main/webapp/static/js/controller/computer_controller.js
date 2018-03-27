'use strict';

angular.module('myApp').controller('ComputerController', ['$scope', 'ComputerService', function($scope, ComputerService) {

    var self = this;
    self.hdd = {id:null,manufacturer:'',model:'',serial:'',capacity:'',hddType:''};
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
        console.log(ComputerService.getComputer());
        window.location='#!computer/motherboard'
    }

    function nextToProcessor() {
        window.location='#!computer/processor'
    }

    function nextToPowerSupply() {
        window.location='#!computer/powersupply'
    }

    function nextToRam() {
        window.location='#!computer/ram'
    }

    function submit() {
        alert("Завершено");
        window.location='/'
    }
}]);