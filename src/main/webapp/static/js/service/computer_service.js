'use strict';

angular.module('myApp').factory('ComputerService', ['$http', '$q', function($http, $q){

    var inner = {};
    var REST_SERVICE_URI = 'http://localhost:8080/computers/';

    var number = 1;

    var factory = {
        setInfo: setInfo,
        setHdd: setHdd,
        getComputer: getComputer,
        setMotherBoard: setMotherBoard,
        setProcessor: setProcessor,
        setPowerSupply: setPowerSupply,
        setRam: setRam,
        createComputer: createComputer,
        fetchAllComputers: fetchAllComputers,
    };


    return factory;

    function fetchAllComputers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while fetching Computers');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function setInfo(info) {
        inner = info;
        console.log(inner);
    }

    function setHdd(hdd) {
        inner.hdd = hdd;
    }

    function setMotherBoard(motherBoard) {
        inner.motherBoard = motherBoard;
    }

    function setProcessor(processor) {
        inner.processor = processor;
    }

    function setPowerSupply(powerSupply) {
        inner.powerSupply = powerSupply;
    }

    function setRam(ram) {
        inner.ram = ram;
    }

    function getComputer() {
        return inner;
    }

    function createComputer() {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, getComputer())
            .then(
                function (response) {
                    handleSuccessCreate();
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    handleErrors(errResponse.status);
                }
            );
        return deferred.promise;
    }

}]);