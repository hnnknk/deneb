'use strict';

angular.module('myApp').controller('ComputerListController', ['$scope', 'ComputerService', function($scope, ComputerService) {

    var self = this;
    self.computers = [];
    self.computer = {};

    self.show = show;

    fetchAllComputers();

    function fetchAllComputers(){
        ComputerService.fetchAllComputers()
            .then(
                function(d) {
                    self.computers = d;
                },
                function(errResponse){
                    console.error('Error while fetching Computers');
                }
            );
    }

    function show(value) {
        $('.nav-tabs a[id = "main-info"]').tab('show');
        for (var i = 0; i < self.computers.length; i++) {
            if(value === self.computers[i].invNumber) {
                self.computer = self.computers[i];
            }
        }
    }

}]);