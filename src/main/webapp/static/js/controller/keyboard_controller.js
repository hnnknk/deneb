'use strict';

angular.module('myApp').controller('KeyboardController', ['$scope', 'KeyboardService', function($scope, KeyboardService) {
    var self = this;
    self.keyboard={id:null,invNumber:'',manufacter:'',model:'',serial:''}
    self.keyboards=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;

    var name = 'keyboard';

    fetchAllKeyboards();

    function fetchAllKeyboards(){
        KeyboardService.fetchAllKeyboards()
            .then(
                function(d) {
                    self.keyboards = d;
                },
                function(errResponse){
                    console.error('Error while fetching Keyboards');
                }
            );
    }

    function createKeyboard(keyboard){
        KeyboardService.createKeyboard(keyboard)
            .then(
                fetchAllKeyboards,
                function(errResponse){
                    console.error('Error while creating Keyboard');
                }
            );
    }

    function updateKeyboard(keyboard, id){
        KeyboardService.updateKeyboard(keyboard,id)
            .then(
                fetchAllKeyboards,
                function(errResponse){
                    console.error('Error while updating Keyboard');
                }
            );
    }

    function deleteKeyboard(id){
        if (confirm('Вы действительно хотите удалить эту клавиатуру?')) {
            KeyboardService.deleteKeyboard(id)
                .then(
                    fetchAllKeyboards,
                    function(errResponse){
                        console.error('Error while deleting Keyboard');
                    }
                );
        }
    }

    function submit() {
        if(self.keyboard.id===null){
            $(".alert").removeClass("in").show();
            $(".alert").delay(1000).addClass("in").fadeOut(4000);
            console.log('Saving New Keyboard', self.keyboard);
            createKeyboard(self.keyboard);
        }else{
            updateKeyboard(self.keyboard, self.keyboard.id);
            console.log('Keyboard updated with id ', self.keyboard.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.keyboards.length; i++){
            if(self.keyboards[i].id === id) {
                self.keyboard = angular.copy(self.keyboards[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.keyboard.id === id) {
            reset();
        }
        deleteKeyboard(id);
    }


    function reset(){
        self.keyboard={id:null,invNumber:'',manufacter:'',model:'',serial:''};
        $scope.myForm.$setPristine();
    }

}]);