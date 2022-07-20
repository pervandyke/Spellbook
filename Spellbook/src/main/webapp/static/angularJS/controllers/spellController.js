'use strict';

angular.module("SpellbookApp").controller("SpellController", ["$scope" ,function($scope){
    $scope.testSpell = {
        name: "Test Spell Name"
    };

    $scope.spells = [
        {
            name: "Fire Bolt"
        },
        {
            name: "Ray of Frost"
        }
    ]
}]);