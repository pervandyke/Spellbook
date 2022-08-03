'use strict';

angular.module("SpellbookApp").factory("SpellService", SpellServiceFactory);

SpellServiceFactory.$inject = ["$http"];

function SpellServiceFactory($http) {

    const REST_SERVICE_URI = "http://localhost:8080/spells/"

    const factory = {
        fetchAllSpells: fetchAllSpells,
        fetchSpellById: fetchSpellById,
        createSpell: createSpell,
        deleteSpell: deleteSpell,
        editSpell: editSpell
    };

    return factory;

    function fetchAllSpells() {
        return $http.get(REST_SERVICE_URI);
    }

    function fetchSpellById(id) {
        return $http.get(REST_SERVICE_URI + id);
    }
    
    function createSpell(spell) {
        return $http.post(REST_SERVICE_URI, spell);   
    }

    function editSpell(spell) {
        return $http.put(REST_SERVICE_URI + spell.id, spell)
    }

    function deleteSpell(id) {
        return $http.delete(REST_SERVICE_URI + id);
    }
}