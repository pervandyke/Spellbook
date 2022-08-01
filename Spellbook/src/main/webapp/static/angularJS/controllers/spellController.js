'use strict';

angular.module("SpellbookApp").controller("SpellController", ["$scope", "$log", "SpellService", function ($scope, $log, SpellService){
    
    const vm = this;
    const newSpellProto = {
        userId: Number(document.getElementById("userId").innerText)
    }
    
    vm.spells = [];
    vm.newSpell = {};

    

    // Initialize the spell list
    fetchAllSpells();
    // Set id for new spell
    vm.newSpell = newSpellProto

    vm.createSpell = createSpell;
    vm.deleteSpell = deleteSpell;
    vm.editSpell = editSpell;

    function fetchAllSpells() {
        SpellService.fetchAllSpells()
            .then(
                function successCallback(response) {
                    vm.spells = response.data;
                },
                function errorCallback(errorResponse) {
                    $log.error("Error occured fetching spells.");
                    $log.error(errorResponse);
                }
            );
    }

    function fetchSpellById(id) {
        return SpellService.fetchSpellById(id)
        .then(
            function successCallback(response) {
                return response.data;
            },
            function errorCallback(errResponse) {
                $log.error("There was an error retrieving the spell.")
                $log.error(errResponse);
            }
        )
    }

    function createSpell() {
        SpellService.createSpell(vm.newSpell)
            .then(
                fetchAllSpells,
                function (errResponse) {
                    $log.error("There was an error creating the spell.")
                    $log.error(errResponse);
                }
            );
        vm.newSpell = newSpellProto;
        $scope.newSpellForm.$setPristine();
    }

    function deleteSpell(id) {
        SpellService.deleteSpell(id)
            .then(
                fetchAllSpells,
                function (errResponse) {
                    $log.error("There was an error deleting the spell.")
                    $log.error(errResponse);
                }
            )
    }

    function editSpell(spell) {
        vm.newSpell = angular.copy(spell);
    } 

}]);