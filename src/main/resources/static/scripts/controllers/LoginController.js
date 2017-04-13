(function () {
    'use strict';

    angular.module('beerApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$mdDialog', '$mdToast', '$state', '$auth'];

    function LoginController($mdDialog, $mdToast, $state, $auth) {
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
            var user = {
                username: vm.credentials.username,
                password: vm.credentials.password
            };
            $auth.login(user)
                .then(function (response) {
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