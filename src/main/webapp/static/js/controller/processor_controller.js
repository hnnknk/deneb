'use strict';

angular.module('myApp').controller('ProcessorController', ['$scope', 'ProcessorService', function($scope, ProcessorService) {
    var self = this;
    self.processor={id:null,manufacter:'',model:'',speed:'',numberOfCores:''};
    self.processors=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    fetchAllProcessors();

    function fetchAllProcessors(){
        ProcessorService.fetchAllProcessors()
            .then(
                function(d) {
                    self.processors = d;
                },
                function(errResponse){
                    console.error('Error while fetching Processors');
                }
            );
    }

    function createProcessor(processor){
        ProcessorService.createProcessor(processor)
            .then(
                fetchAllProcessors,
                function(errResponse){
                    console.error('Error while creating Processor');
                }
            );
    }

    function updateProcessor(processor, id){
        ProcessorService.updateProcessor(processor,id)
            .then(
                fetchAllProcessors,
                function(errResponse){
                    console.error('Error while updating Processor');
                }
            );
    }

    function deleteProcessor(id){
        if (confirm('Вы действительно хотите удалить этот процессор?')) {
            ProcessorService.deleteProcessor(id)
                .then(
                    fetchAllProcessors,
                    function(errResponse){
                        console.error('Error while deleting Processor');
                    }
                );
        }
    }

    function submit() {
        if(self.processor.id===null){
            $(".alert").removeClass("in").show();
            $(".alert").delay(1000).addClass("in").fadeOut(4000);
            console.log('Saving New Processor', self.processor);
            createProcessor(self.processor);
        }else{
            updateProcessor(self.processor, self.processor.id);
            console.log('Processor updated with id ', self.processor.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.processors.length; i++){
            if(self.processors[i].id === id) {
                self.processor = angular.copy(self.processors[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.processor.id === id) {
            reset();
        }
        deleteProcessor(id);
    }


    function reset(){
        self.processor={id:null,manufacter:'',model:'',speed:'',numberOfCores:''};
        $scope.myForm.$setPristine();
    }

}]);