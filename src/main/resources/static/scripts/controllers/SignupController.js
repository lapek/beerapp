(function () {
    'use strict';

    angular.module('beerApp')
        .controller('SignupController', SignupController);

    SignupController.$inject = ['$log', '$mdToast', '$auth', '$state'];

    function SignupController($log, $mdToast, $auth, $state) {
        var vm = this;

        vm.user = {};
        vm.user.username = '';
        vm.user.password = '';
        vm.user.email = '';
        vm.register = register;

        function register() {
            var user = {
                username: vm.user.username,
                email: vm.user.email,
                password: vm.user.password
            };
            $auth.signup(user)
                .then(function (response) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Zarejestrowano.')
                            .position("top left")
                            .hideDelay(2000)
                    );
                    $state.go('home');
                })
                .catch(function (response) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Błąd podczas rejestracji.')
                            .position("top left")
                            .hideDelay(8000)
                    );
                })
        }
    }

})();
