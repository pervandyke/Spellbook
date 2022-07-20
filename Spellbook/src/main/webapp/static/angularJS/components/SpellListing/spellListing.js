'use strict';
angular.module("SpellbookApp").component("spellListing", {
    templateUrl: "/static/angularJS/components/SpellListing/spellListing.html",
    bindings: {
        spell: '<'
    }
});