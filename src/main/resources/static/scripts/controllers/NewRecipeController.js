(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NewRecipeController', NewRecipeController);

    NewRecipeController.$inject = ['$mdDialog', '$mdToast', '$scope', '$location', '$http', '$compile', '$q', '$log', '$rootScope', 'GrainService', 'HopStoreService', 'MashingService'];

    function NewRecipeController($mdDialog, $mdToast, $scope, $location, $http, $compile, $log, $q, $rootScope, GrainService, HopStoreService, MashingService) {
        var vm = this;

        vm.recipe = {};
        vm.recipe.style = '';
        vm.recipe.name = '';
        vm.recipe.author = '';
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
        vm.recipe.mashing = MashingService.getList();

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

        function saveRecipe() {
            var grainList = [];
            var hopStoreList = [];
            var mashingList = [];
            var temp = {};

            angular.forEach(vm.recipe.grain, function (grain, index) {
                temp = {};
                temp.weight = grain.weight;
                temp.maltID = grain.malt.id_malt;
                grainList.push(temp);
            });

            angular.forEach(vm.recipe.hopStore, function (hop, index) {
                temp = {};
                temp.weight = hop.weight;
                temp.time = hop.time;
                temp.hopID = hop.hop.id_hop;
                hopStoreList.push(temp);
            });

            angular.forEach(vm.recipe.mashing, function (mash, index) {
                temp = {};
                temp.amount = mash.amount;
                temp.temperature = mash.temperature;
                temp.time = mash.time;
                mashingList.push(temp);
            });

            var request = {
                name: vm.recipe.name,
                author: vm.recipe.author,
                visible: vm.recipe.visible,
                style: vm.recipe.style.name,
                batchSize: vm.recipe.batchSize,
                estBoilSize: vm.recipe.estBoilSize,
                estBoilTime: vm.recipe.estBoilTime,
                efficiency: vm.recipe.efficiency,
                fermentationDTO: {
                    primaryTime: vm.recipe.fermentation.primary.time,
                    primaryTemperature: vm.recipe.fermentation.primary.temperature,
                    secondaryTime: vm.recipe.fermentation.secondary.time,
                    secondaryTemperature: vm.recipe.fermentation.secondary.temperature
                },
                yeastID: vm.recipe.yeast.id_yeast,
                grainList: grainList,
                hopStoreList: hopStoreList,
                mashingList: mashingList
            };

            console.log('request: ', request);

            $http({
                method: 'POST',
                url: '/api/recipe/save',
                data: angular.toJson(request)
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
            } else {
                showToast('Proszę uzupełnić zasyp.')
            }

            if (vm.recipe.hopStore.length > 0) {
                calculateIBU();
            } else {
                showToast('Proszę uzupełnić chmielenie.')
            }

            if ($rootScope.currentUser != undefined) {
                vm.recipe.author = $rootScope.currentUser;
            }

            if (vm.recipe.mashing.length == 0) {
                showToast('Proszę uzupełnić zacieranie.')
            }

            if (vm.recipe.yeast == null) {
                showToast('Proszę wybrać drożdże.')
            }

            //console.log('recipe: ', vm.recipe);
            if(vm.recipe.grain.length > 0 && vm.recipe.hopStore.length > 0 && vm.recipe.mashing.length > 0 && vm.recipe.yeast != null) {
                saveRecipe();
                showToast('Zapisano recepturę');
                $location.path("/");
            } else {
                //showToast('Proszę uzupełnić recepturę.')
            }

        }

        function showToast(message){
            $mdToast.show(
                $mdToast.simple()
                    .textContent(message)
                    .position("top right")
                    .hideDelay(3000)
            );
        }


        $scope.$watchCollection('vm.recipe.grain', function () {
            if (vm.recipe.grain.length > 0) {
                calculateColor();
                calculateSG();
            }
        }, true);

        $scope.$watchCollection('vm.recipe.hopStore', function () {
            if (vm.recipe.hopStore.length > 0) {
                calculateIBU();
            }
        }, true);

    }
})();