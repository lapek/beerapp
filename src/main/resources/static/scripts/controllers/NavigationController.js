(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$mdDialog', '$mdToast', '$log', '$scope', '$http', '$rootScope', '$state'];

    function NavigationController($mdDialog, $mdToast, $log, $scope, $http, $rootScope, $state) {
        var vm = this;

        vm.showLogin = showLogin;
        vm.openMenu = openMenu;
        vm.logout = logout;

        function openMenu($mdOpenMenu, $event) {
            vm.originatorEv = $event;
            $mdOpenMenu($event);
        }

        function showLogin($event) {
            if ($rootScope.authenticated != true) {
                $mdDialog.show({
                    templateUrl: '../../views/login.html',
                    parent: angular.element(document.body),
                    targetEvent: $event,
                    clickOutsideToClose: true,
                    fullscreen: $scope.customFullscreen
                });
            }
        }

        function logout() {
            console.log('Logging out...');
            $http.post('logout', {
            }).success(function () {
                $rootScope.authenticated = false;
                $rootScope.currentUser = null;
                window.localStorage.setItem('CurrentUser', null);
                $state.go("home");
            }).error(function (data) {
                $rootScope.authenticated = false;
                $rootScope.currentUser = null;
                window.localStorage.setItem('CurrentUser', null);

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