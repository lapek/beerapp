(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NewRecipeController', NewRecipeController);

    NewRecipeController.$inject = ['$mdDialog', '$scope', '$http', '$compile', '$log', 'GrainService', 'HopStoreService'];

    function NewRecipeController($mdDialog, $scope, $http, $compile, $log, GrainService, HopStoreService) {
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
        vm.recipe.thickness = 3;
        vm.recipe.ferment = [];
        vm.recipe.grain = [];
        vm.recipe.hopStore = [];
        vm.recipe.fermentation = {};
        vm.recipe.fermentation.primary = {};
        vm.recipe.fermentation.primary.time = 7;
        vm.recipe.fermentation.primary.temperature = 18;
        vm.recipe.fermentation.secondary = {};
        vm.recipe.fermentation.secondary.time = 0;
        vm.recipe.fermentation.secondary.temperature = 0;
        vm.recipe.yeast = {};

        vm.recipe.details = {};
        vm.recipe.details.OG = 0.0;
        vm.recipe.details.FG = 0.0;
        vm.recipe.details.ABV = 0.0;
        vm.recipe.details.IBU = 0.0;
        vm.recipe.details.SRM = 0.0;
        vm.color = null;
        vm.styles = null;
        vm.yeasts = null;
        vm.getStylesList = getStylesList;
        vm.getYeastsList = getYeastsList;
        vm.addMalt = addMalt;
        vm.addHop = addHop;
        vm.addMashStep = addMashStep;
        vm.save = save;

        vm.recipe.grain = GrainService.getList();
        vm.recipe.hopStore = HopStoreService.getList();

        /*Get Styles List from api*/
        function getStylesList() {
            $http({
                method: "GET",
                url: '/api/styles/list'
            }).then(function mySuccess(response) {
                vm.styles = vm.styles || response.data;
            }, function myError(response) {
                vm.styles = null;
            });
        }

        /*Get Yeasts List from api*/
        function getYeastsList() {
            $http({
                method: "GET",
                url: '/api/yeast/list'
            }).then(function mySuccess(response) {
                vm.yeasts = vm.yeasts || response.data;
            }, function myError(response) {
                vm.yeasts = null;
            });
        }

        function addMalt() {
            var divElement = angular.element(document.querySelector('#maltDiv'));
            var appendHtml = $compile('<add-Malt></add-Malt>')($scope);
            divElement.append(appendHtml);
        }

        function addHop() {
            var divElement = angular.element(document.querySelector('#hopDiv'));
            var appendHtml = $compile('<add-Hop></add-Hop>')($scope);
            divElement.append(appendHtml);
        }

        function addMashStep() {
            var divElement = angular.element(document.querySelector('#mashDiv'));
            var appendHtml = $compile('<add-Mashing></add-Mashing>')($scope);
            divElement.append(appendHtml);
        }

        $scope.$watch('vm.recipe.batchSize', 'vm.recipe.grain', function () {
            calculateColor();
            calculateSG();
        });

        /* Calculate SRM and return hexColor */
        function calculateColor() {
            var request = [];
            var temp = {};
            angular.forEach(vm.recipe.grain, function (malt, index) {
                temp = {};
                temp.weight = malt.weight;
                temp.colorEBC = malt.malt.colorEBC;
                temp.batchSize = vm.recipe.batchSize;
                request.push(temp);
            });
            $http({
                method: 'POST',
                url: '/api/recipe/color',
                data: angular.toJson(request),
                params: {
                    'batchSize': vm.recipe.batchSize
                }
            }).then(function mySuccess(response) {
                vm.recipe.details.SRM = response.data.SRM;
                vm.color = response.data.color;
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
                request.push(temp);
            });
            $http({
                method: 'POST',
                url: '/api/recipe/sg',
                data: angular.toJson(request),
                params: {
                    'batchSize': vm.recipe.batchSize,
                    'efficiency': vm.recipe.efficiency,
                    'attenuation': vm.recipe.attenuation
                }
            }).then(function mySuccess(response) {
                vm.recipe.details.OG = response.data.OG;
                vm.recipe.details.FG = response.data.FG;
                vm.recipe.details.ABV = response.data.ABV;
            }, function myError(response) {
                //error
            });
        }

        /* Calculate IBU */
        function calculateIBU() {
            var request = [];
            var temp = {};
            angular.forEach(vm.recipe.hopStore, function (hop, index) {
                temp = {};
                temp.weight = hop.weight;
                temp.time = hop.time;
                temp.alpha = hop.hop.alpha;
                request.push(temp);
            });
            $http({
                method: 'POST',
                url: '/api/recipe/ibu',
                data: angular.toJson(request),
                params: {
                    'estBoilSize': vm.recipe.estBoilSize,
                    'batchSize': vm.recipe.batchSize,
                    'gravity': vm.recipe.details.OG
                }
            }).then(function mySuccess(response) {
                vm.recipe.details.IBU = response.data.IBU;
            }, function myError(response) {
                //error
            });
        }

        /*Save recipe*/
        function save() {
            if (vm.recipe.grain.length > 0) {
                calculateColor();
                calculateSG();
            }
            if (vm.recipe.hopStore.length > 0) {
                calculateIBU();
            }

            $log.info('recipe: ', vm.recipe)
        }


    }
})();