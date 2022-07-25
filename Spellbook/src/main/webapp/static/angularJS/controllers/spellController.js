'use strict';

angular.module("SpellbookApp").controller("SpellController", ["$scope", "SpellService", function ($scope, SpellService){
    
    const vm = this;
    
    vm.spells = [];
    vm.newSpell = {};

    // Initialize the spell list
    fetchAllSpells();

    vm.createSpell = createSpell;
    vm.deleteSpell = deleteSpell;

    function fetchAllSpells() {
        console.log("Fetching all Spells");
        SpellService.fetchAllSpells()
            .then(
                function successCallback(response) {
                    vm.spells = response.data;
                },
                function errorCallback(errorResponse) {
                    console.log("Error occured fetching spells.");
                    console.log(errorResponse);
                }
            );
    }

    function createSpell() {
        console.log("Creating Spell " + vm.newSpell.name);
        SpellService.createSpell(vm.newSpell)
            .then(
                fetchAllSpells,
                function (errResponse) {
                    console.log("There was an error creating the spell.")
                    console.log(errResponse);
                }
            );
        vm.newSpell = {};
        $scope.newSpellForm.$setPristine();
    }

    function deleteSpell(id) {
        console.log("Deleting spell " + id)
        SpellService.deleteSpell(id)
            .then(
                fetchAllSpells,
                function (errResponse) {
                    console.log("There was an error deleting the spell.")
                    console.error(errResponse);
                }
            )
    }

}]);