'use strict';

angular.module("SpellbookApp").factory("SpellService", SpellServiceFactory);

SpellServiceFactory.$inject = ["$http"];

function SpellServiceFactory($http) {

    const REST_SERVICE_URI = "http://localhost:8081/"

    const factory = {
        fetchAllSpells: fetchAllSpells,
        fetchSpellById: fetchSpellById,
        fetchUsersSpells: fetchUsersSpells,
        createSpell: createSpell,
        deleteSpell: deleteSpell,
        editSpell: editSpell
    };

    return factory;

    function fetchAllSpells() {
        return $http.get(REST_SERVICE_URI + "spells");
    }

    function fetchUsersSpells(userId) {
        return $http.get(REST_SERVICE_URI + "user/" + userId + "/spells")
    }

    function fetchSpellById(id) {
        return $http.get(REST_SERVICE_URI + "spells/" + id);
    }
    
    function createSpell(spell) {
        return $http.post(REST_SERVICE_URI + "spells", spell);   
    }

    function editSpell(spell) {
        return $http.put(REST_SERVICE_URI + "spells/" + spell.id, spell)
    }

    function deleteSpell(id) {
        return $http.delete(REST_SERVICE_URI + "spells/" + id);
    }
}