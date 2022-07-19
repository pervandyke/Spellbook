(function(angular) {
    'use strict'
    angular.module("SpellbookApp").component("spellListing", {
        templateUrl: "spellListing.html",
        bindings: {
            spell: '<'
        }
    });
})(window.angular);