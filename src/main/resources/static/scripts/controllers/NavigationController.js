(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$mdDialog', '$mdToast', '$scope', '$rootScope', '$state', '$auth', 'AccountService'];

    function NavigationController($mdDialog, $mdToast, $scope, $rootScope, $state, $auth, AccountService) {
        var vm = this;
        var originatorEv;

        vm.currentUser = '';

        vm.showLogin = showLogin;
        vm.openMenu = openMenu;
        vm.logout = logout;

        function init() {
            if ($auth.isAuthenticated()) {
                getUsername();
            }
        }

        function openMenu($mdMenu, $event) {
            originatorEv = $event;
            $mdMenu.open($event);
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
                    $state.go("app.home");
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Wylogowano.')
                            .position("top left")
                            .hideDelay(2000)
                    );
                });
        }

        function getUsername() {
            AccountService.getProfile()
                .then(function successCallback(response) {
                    vm.currentUser = response.data.username;
                }, function errorCallback(response) {
                    console.log('Get profile error: ', response)
                });
        }

        init();
    }
})();