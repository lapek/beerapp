(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NewRecipeController', NewRecipeController);

    NewRecipeController.$inject = ['$mdDialog', '$scope'];

    function NewRecipeController($mdDialog, $scope) {
        var vm = this;

        // vm.addMalt = addMalt;
        //
        // function addMalt($event) {
        // }
        
    }
})();