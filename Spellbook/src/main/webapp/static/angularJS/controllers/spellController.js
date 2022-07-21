'use strict';

angular.module("SpellbookApp").controller("SpellController", ["SpellService", function (SpellService){
    
    const vm = this;
    
    vm.spells = [];
    vm.newSpell = {};

    // Initialize the spell list
    fetchAllSpells();

    vm.createSpell = createSpell;

    function fetchAllSpells() {
        console.log("Fetching all Spells");
        SpellService.fetchAllSpells()
            .then(
                function successCallback(response) {
                    console.log(response);
                    vm.spells = response.data;
                },
                function errorCallback(errorResponse) {
                    console.log(errorResponse);
                    console.log("Error occured fetching spells.");
                }
            );
    }

    function createSpell() {
        console.log("Creating Spell " + vm.newSpell.name);
        SpellService.createSpell(vm.newSpell)
            .then(
                fetchAllSpells,
                function (errResponse) {
                    console.log(errResponse);
                    console.log("There was an error creating the spell.")
                }
            );
    }

}]);