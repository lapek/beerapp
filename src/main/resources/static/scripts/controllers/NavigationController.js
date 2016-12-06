(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$mdDialog', '$mdToast', '$log', '$scope', '$http', '$rootScope', '$location'];

    function NavigationController($mdDialog, $mdToast, $log, $scope, $http, $rootScope, $location) {
        var vm = this;

        vm.showLogin = showLogin;
        vm.openMenu = openMenu;
        vm.logout = logout;

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

        function logout() {
            $log.info('Logging out...');
            $http.post('logout', {}).success(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            }).error(function (data) {
                $rootScope.authenticated = false;
            });
            $mdToast.show(
                $mdToast.simple()
                    .textContent('Wylogowano.')
                    .position("top left")
                    .hideDelay(2000)
            );
        }

    }
})();