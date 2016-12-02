(function () {
    'use strict';

    angular
        .module('beerApp')
        .directive('addMashing', addMashing);

    AddMashingController.$inject = ['$rootScope', '$scope', '$element', '$http', '$log', 'MashingService'];

    function addMashing() {
        var directive = {
            restrict: "E",
            scope: {},
            templateUrl: '../views/addMashStep.html',
            controller: AddMashingController,
            controllerAs: 'amVm',
            bindToController: true
        };

        return directive;
    }

    function AddMashingController($rootScope, $scope, $element, $http, $log, MashingService) {
        var vm = this;

        vm.mashing = {};
        vm.mashing.time = '';
        vm.mashing.temperature = '';
        vm.mashing.amount = '';

        vm.Delete = Delete;

        /*Delete row*/
        function Delete(e) {
            GrainService.removeList(vm.grain);
            $element.remove();
            $scope.$destroy();
        }

        $scope.$watch('vm.mashing', function(current, original) {
            MashingService.addList(vm.mashing);
        });

    }

})();