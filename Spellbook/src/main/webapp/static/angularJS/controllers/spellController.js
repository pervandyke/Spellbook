'use strict';

angular.module("SpellbookApp").controller("SpellController", ["$scope", "$log", "SpellService", function ($scope, $log, SpellService){
    
    const vm = this;
    const newSpellProto = {}
    
    vm.editing = false;
    vm.spells = [];
    vm.newSpell = {};

    // Initialize the spell list
    fetchAllSpells();
    // Set userId for new spell
    vm.newSpell = angular.copy(newSpellProto);

    vm.createSpell = createSpell;
    vm.deleteSpell = deleteSpell;
    vm.editSpell = editSpell;
    vm.toggleEdit = toggleEdit;

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

        vm.newSpell.userId = Number(document.getElementById("userId").innerText);

        if (vm.newSpell.id != null) {
            // edit extant spell
            SpellService.editSpell(vm.newSpell)
                .then(
                    fetchAllSpells,
                    function (errResponse) {
                        $log.error("There was an error editing the spell.");
                        $log.error(errResponse);
                    }
                );
        } else {
            // create new spell
            SpellService.createSpell(vm.newSpell)
            .then(
                fetchAllSpells,
                function (errResponse) {
                    $log.error("There was an error creating the spell.")
                    $log.error(errResponse);
                }
            );
        }

        vm.newSpell = angular.copy(newSpellProto);
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
            );
    }

    function editSpell(spell) {
        vm.newSpell = angular.copy(spell);
    }

    function toggleEdit() {
        if(vm.editing) {
            vm.editing = false;
        } else {
            vm.editing = true;
        }
    }

}]);