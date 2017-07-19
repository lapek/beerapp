(function () {
    'use strict';

    angular
        .module('beerApp')
        .directive('addMalt', addMalt);

    AddMaltController.$inject = ['$scope', '$element', '$http', 'GrainService'];

    function addMalt() {
        return {
            restrict: "E",
            scope: {},
            templateUrl: '../views/addMalt.html',
            controller: AddMaltController,
            controllerAs: 'adVm',
            bindToController: true
        };
    }

    function AddMaltController($scope, $element, $http, GrainService) {
        var vm = this;

        vm.malts = null;

        vm.grain = {};
        vm.grain.weight = '';
        vm.grain.malt = '';

        vm.Delete = Delete;
        vm.getMaltsList = getMaltsList;

        /*Get Malts List from api*/
        function getMaltsList() {
            $http({
                method: "GET",
                url: '/api/malts/list'
            }).then(function mySuccess(response) {
                vm.malts = vm.malts || response.data;
            }, function myError(response) {
                vm.malts = 'Błąd';
            });
        }

        /*Delete row*/
        function Delete(e) {
            GrainService.removeList(vm.grain);
            $element.remove();
            $scope.$destroy();
        }

        $scope.$watch('vm.grain', function(current, original) {
            GrainService.addList(vm.grain);
        });
        
    }

})();