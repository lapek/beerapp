(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$mdDialog', '$mdToast', '$scope', '$rootScope', '$state', '$auth', 'AccountService'];

    function NavigationController($mdDialog, $mdToast, $scope, $rootScope, $state, $auth, AccountService) {
        var vm = this;

        vm.currenUser = '';

        vm.showLogin = showLogin;
        vm.openMenu = openMenu;
        vm.logout = logout;
        vm.getUsername = getUsername;
        vm.init = init;

        function init() {
            vm.getUsername();
        }

        function openMenu($mdOpenMenu, $event) {
            vm.originatorEv = $event;
            $mdOpenMenu($event);
        }

        function showLogin($event) {
            if (!$auth.isAuthenticated()) {
                $mdDialog.show({
                    templateUrl: '../../views/login.html',
                    parent: angular.element(document.body),
                    targetEvent: $event,
                    clickOutsideToClose: true,
                    fullscreen: $scope.customFullscreen
                });
            }
        }

        $scope.isAuthenticated = function () {
            return $auth.isAuthenticated();
        };

        function logout() {
            $auth.logout()
                .then(function () {
                    $state.go("home");
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Wylogowano.')
                            .position("top left")
                            .hideDelay(2000)
                    );
                });
        }

        function getUsername() {
            if ($auth.isAuthenticated()) {
                AccountService.getProfile()
                    .success(function(data) {
                        console.log(data);
                        vm.currenUser = data.username;
                    })
                    .error(function(error) {
                        console.log('Get profile error: ', error)
                    });
            }
        }

    }
})();