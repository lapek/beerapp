(function () {
    'use strict';

    angular.module('beerApp')
        .controller('myRecipesController', myRecipesController);

    myRecipesController.$inject = ['$scope', '$mdDialog', '$http', '$rootScope', '$window', '$timeout'];

    function myRecipesController($scope, $mdDialog, $http, $rootScope, $window, $timeout) {
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
            }).success(function (response) {
                vm.recipes = response;
                vm.recipeToShow = vm.recipes[0];
            }).error(function (response) {
                //
            });
        }

        function goToRecipe(recipe, $event) {
            vm.recipeToShow = recipe;
        }


    }
})();