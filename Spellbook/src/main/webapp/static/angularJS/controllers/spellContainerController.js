'use strict';

angular.module("SpellbookApp").controller("SpellContainerController", ["$scope", function ($scope){
    
    const vm = this;
    vm.editing = false;
    
    vm.toggleEdit = toggleEdit;
    
    function toggleEdit() {
        if(vm.editing) {
            vm.editing = false;
        } else {
            vm.editing = true;
        }
        console.log("Toggle Edit to " + vm.editing)
    }
}]);