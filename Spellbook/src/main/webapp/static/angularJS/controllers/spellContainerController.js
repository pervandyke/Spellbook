'use strict';

angular.module("SpellbookApp").controller("SpellContainerController", ["$scope", function ($scope){
    
    const vm = this;
    let editing = false;
    
    vm.toggleEdit = toggleEdit;
    
    function toggleEdit() {
        if(editing) {
            editing = false;
        } else {
            editing = true;
        }
    }
}]);