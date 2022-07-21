'use strict';

angular.module("SpellbookApp").controller("SpellController", ["$scope", "SpellService", function ($scope, SpellService){
    
    const vm = this;
    
    vm.spells = [
        {
            name: "Fire Bolt",
            level: Number(0),
            castingTime: "1 Action",
            range: "120ft",
            damageAmount: "1d10",
            damageType: "Fire",
            components: "V,S",
            duration: "Instantaneous",
            description: `You hurl a mote of fire at a creature or object within range. Make a ranged spell attack against the target. On a hit, the target takes 1d10 fire damage. A flammable object hit by this spell ignites if it isn’t being worn or carried. 
            This spell’s damage increases by 1d10 when you reach 5th level (2d10), 11th level (3d10), and 17th level (4d10).`
        },
        {
            name: "Ray of Frost",
            level: Number(0),
            castingTime: "1 Action",
            range: "60ft",
            damageAmount: "1d8",
            damageType: "Cold",
            components: "V,S",
            duration: "Instantaneous",
            description: `A frigid beam of blue-white light streaks toward a creature within range. Make a ranged spell attack against the target. On a hit, it takes 1d8 cold damage, and its speed is reduced by 10 feet until the start of your next turn.
            The spell's damage increases by 1d8 when you reach 5th level (2d8), 11th level (3d8), and 17th level (4d8).`
        }
    ]

    // Initialize the spell list
    //fetchAllSpells();

    function fetchAllSpells() {
        SpellService.fetchAllSpells()
            .then(
                function successCallback(fetchedSpells) {
                    $scope.spells = fetchedSpells;
                },
                function errorCallback(errorResponse) {
                    console.log("Error occured fetching spells.")
                }
            );
    }

    function createSpell(spell) {
        SpellService.createSpell(spell)
            .then(
                fetchAllSpells,
                function (errResponse) {
                    console.log("There was an error creating the spell.")
                }
            );
    }

}]);