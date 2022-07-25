'use strict';

angular.module("SpellbookApp").factory("SpellService", SpellServiceFactory);

SpellServiceFactory.$inject = ["$http"];

function SpellServiceFactory($http) {

    const REST_SERVICE_URI = "http://localhost:8080/spells/"

    const factory = {
        fetchAllSpells: fetchAllSpells,
        createSpell: createSpell,
        deleteSpell: deleteSpell
    };

    return factory;

    function fetchAllSpells() {
        return $http.get(REST_SERVICE_URI);
    }
    
    function createSpell(spell) {
        return $http.post(REST_SERVICE_URI, spell);
            
    }

    function deleteSpell(id) {
        return $http.delete(REST_SERVICE_URI + id);
    }
}