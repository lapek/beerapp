(function () {
    'use strict';

    angular
        .module('beerApp')
        .directive('addMashing', addMashing);

    AddMashingController.$inject = ['$scope', '$element', 'MashingService'];

    function addMashing() {
        return {
            restrict: "E",
            scope: {},
            templateUrl: '../views/addMashStep.html',
            controller: AddMashingController,
            controllerAs: 'amVm',
            bindToController: true
        };
    }

    function AddMashingController($scope, $element, MashingService) {
        var vm = this;

        vm.mashing = {};

        vm.mashing.time = '';
        vm.mashing.temperature = '';
        vm.mashing.amount = '';

        vm.Delete = Delete;

        /*Delete row*/
        function Delete(e) {
            GrainService.removeList(vm.mashing);
            $element.remove();
            $scope.$destroy();
        }

        $scope.$watch('vm.mashing', function (current, original) {
            MashingService.addList(vm.mashing);
        });
    }

})();