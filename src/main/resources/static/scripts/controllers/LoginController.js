(function () {
    'use strict';

    angular.module('beerApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$mdDialog', '$mdToast', '$state', '$rootScope', '$auth', 'AccountService'];

    function LoginController($mdDialog, $mdToast, $state, $rootScope, $auth, AccountService) {
        var vm = this;

        vm.cancel = cancel;
        vm.login = login;
        vm.credentials = {};
        vm.credentials.username = '';
        vm.credentials.password = '';

        function cancel() {
            $mdDialog.hide();
        }

        function login($event) {
            $auth.login(vm.credentials)
                .then(function (response) {
                    AccountService.getProfile()
                        .then(function successCallback(data) {
                            $rootScope.user = data;
                        });
                    $state.go("home");
                    successToast();
                    cancel();
                })
                .catch(function (response) {
                    errorToast();
                    vm.credentials.password = '';
                });
        }

        function errorToast() {
            $mdToast.show(
                $mdToast.simple()
                    .textContent('Problem z logowaniem, spróbuj ponownie.')
                    .position("top left")
                    .hideDelay(10000)
            );
        }

        function successToast() {
            $mdToast.show(
                $mdToast.simple()
                    .textContent('Zalogowano.')
                    .position("top left")
                    .hideDelay(2000)
            );
        }

    }
})();