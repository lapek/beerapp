(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$mdDialog', '$scope'];

    function NavigationController($mdDialog, $scope) {
        var vm = this;
        
        vm.showLogin = showLogin;
        vm.openMenu = openMenu;

        function openMenu($mdOpenMenu, $event) {
            vm.originatorEv = $event;
            $mdOpenMenu($event);
        }

        function showLogin($event) {
            $mdDialog.show({
                templateUrl: '../../views/login.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen
            });
        }
    }
})();