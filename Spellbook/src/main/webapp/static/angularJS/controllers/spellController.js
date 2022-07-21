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
                function successCallback(fetchedSpells) {
                    vm.spells = fetchedSpells;
                },
                function errorCallback(errorResponse) {
                    console.log("Error occured fetching spells.")
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
                }
            );
    }

}]);