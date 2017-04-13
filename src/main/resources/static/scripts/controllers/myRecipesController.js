(function () {
    'use strict';

    angular.module('beerApp')
        .controller('myRecipesController', myRecipesController);

    myRecipesController.$inject = ['$scope', '$mdDialog', '$http', '$rootScope', '$timeout'];

    function myRecipesController($scope, $mdDialog, $http, $rootScope, $timeout) {
        var vm = this;

        vm.recipes = [];
        vm.recipeToShow = {};

        vm.init = init;
        vm.getAllUserRecipes = getAllUserRecipes;
        vm.goToRecipe = goToRecipe;

        function init() {
            vm.getAllUserRecipes();
        }

        function getAllUserRecipes() {
            $http({
                method: 'GET',
                url: '/api/recipes/list/user',
                params: {
                    author: $rootScope.currentUser
                }
            }).then(function onSuccess(response) {
                vm.recipes = response;
                vm.recipeToShow = vm.recipes[0];
            }, function onError(response) {
                //
            });
        }

        function goToRecipe(recipe, $event) {
            vm.recipeToShow = recipe;
        }


    }
})();