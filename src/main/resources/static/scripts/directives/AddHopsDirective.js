(function () {
    'use strict';

    angular
        .module('beerApp')
        .directive('addHop', addHop);

    AddHopController.$inject = ['$scope', '$element', '$http', 'HopStoreService'];

    function addHop() {
        return {
            restrict: "E",
            scope: {},
            templateUrl: '../views/addHop.html',
            controller: AddHopController,
            controllerAs: 'ahVm',
            bindToController: true
        };
    }

    function AddHopController($scope, $element, $http, HopStoreService) {
        var vm = this;

        vm.hops = null;

        vm.hopStore = {};
        vm.hopStore.weight = '';
        vm.hopStore.time = '';
        vm.hopStore.hop = '';

        vm.Delete = Delete;
        vm.getHopsList = getHopsList;

        /*Get Malts List from api*/
        function getHopsList() {
            $http({
                method: "GET",
                url: '/api/hops/list'
            }).then(function mySuccess(response) {
                vm.hops = vm.hops || response.data;
            }, function myError(response) {
                vm.hops = 'Błąd';
            });
        }

        /*Delete row*/
        function Delete(e) {
            HopStoreService.removeList(vm.hopStore);
            $element.remove();
            $scope.$destroy();
        }

        $scope.$watch('vm.hopStore', function(current, original) {
            HopStoreService.addList(vm.hopStore);
        });

    }

})();