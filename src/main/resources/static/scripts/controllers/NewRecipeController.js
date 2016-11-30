(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NewRecipeController', NewRecipeController);

    NewRecipeController.$inject = ['$mdDialog', '$scope', '$http', '$compile', '$log', 'GrainService'];

    function NewRecipeController($mdDialog, $scope, $http, $compile, $log, GrainService) {
        var vm = this;

        vm.recipe = {};
        vm.recipe.style = '';
        vm.recipe.name = '';
        vm.recipe.visible = true;
        vm.recipe.batchSize = 20;
        vm.recipe.estBoilTime = 60;
        vm.recipe.estBoilSize = 27;
        vm.recipe.efficiency = 75;
        vm.recipe.attenuation = 75;
        vm.recipe.grain = [];
        vm.recipe.store = [];

        vm.recipe.details = {};
        vm.recipe.details.OG = 0.0;
        vm.recipe.details.FG = 0.0;
        vm.recipe.details.ABV = 0.0;
        vm.recipe.details.IBU = 0.0;
        vm.recipe.details.SRM = 0.0;

        vm.color = null;
        vm.styles = null;
        vm.getStylesList = getStylesList;
        vm.addMalt = addMalt;
        vm.save = save;

        vm.recipe.grain = GrainService.getList();

        /*Get Styles List from api*/
        function getStylesList() {
            $http({
                method: "GET",
                url: '/api/styles/list'
            }).then(function mySuccess(response) {
                vm.styles = vm.styles || response.data;
            }, function myError(response) {
                vm.styles = 'Błąd';
            });
        }

        function addMalt() {
            var divElement = angular.element(document.querySelector('#maltDiv'));
            var appendHtml = $compile('<add-Malt></add-Malt>')($scope);
            divElement.append(appendHtml);
        }

        $scope.$watch('vm.recipe.batchSize', 'vm.recipe.grain', function () {
            calculateColor();
        });

        /* Calculate SRM and return hexColor */
        function calculateColor() {
            var request = [];
            var temp = {};
            angular.forEach(vm.recipe.grain, function (malt, index) {
                temp = {};
                temp.grain = malt.weight;
                temp.colorEBC = malt.malt.colorEBC;
                temp.batchSize = vm.recipe.batchSize;
                request.push(temp);
            });
            $http({
                method: 'POST',
                url: '/api/recipe/color',
                data: angular.toJson(request)
            }).then(function mySuccess(response) {
                vm.recipe.details.SRM = response.data.SRM;
                vm.color = response.data.Color;
            }, function myError(response) {
                //error
            });
        }

        /* Calculate OG */
        function calculateSG() {
            var request = [];
            var temp = {};
            angular.forEach(vm.recipe.grain, function (malt, index) {
                temp = {};
                temp.weight = malt.weight;
                temp.potential = malt.malt.potential;
                temp.batchSize = vm.recipe.batchSize;
                temp.efficiency = vm.recipe.efficiency;
                temp.attenuation = vm.recipe.attenuation;
                request.push(temp);
            });
            $http({
                method: 'POST',
                url: '/api/recipe/sg',
                data: angular.toJson(request)
            }).then(function mySuccess(response) {
                vm.recipe.details.OG = response.data.OG;
                vm.recipe.details.FG = response.data.FG;
                vm.recipe.details.ABV = response.data.ABV;
            }, function myError(response) {
                //error
            });
        }

        /*Save recipe*/
        function save() {
            if (vm.recipe.grain != null) {
                calculateColor();
                calculateSG();
            }
            $log.info('recipe: ', vm.recipe)
        }


    }
})();