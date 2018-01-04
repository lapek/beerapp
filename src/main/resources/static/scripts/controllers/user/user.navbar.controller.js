(function () {
    'use strict';

    angular.module('beerApp')
        .controller('UserNavigationController', UserNavigationController);

    UserNavigationController.$inject = ['$mdToast', '$scope', '$rootScope', '$state', '$auth', 'AccountService'];

    function UserNavigationController($mdToast, $scope, $rootScope, $state, $auth, AccountService) {
        var vm = this;
        var originatorEv;

        vm.currentUser = '';

        vm.openMenu = openMenu;
        vm.logout = logout;
        vm.$onInit = onInit;

        function openMenu($mdMenu, $event) {
            originatorEv = $event;
            $mdMenu.open($event);
        }

        function logout() {
            $auth.logout()
                .then(function () {
                    $state.go("app.anon.home");
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

        function onInit() {
            getUsername();
        }
    }
})();