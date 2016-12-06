(function () {
    'use strict';

    angular.module('beerApp')
        .controller('SignupController', SignupController);

    SignupController.$inject = ['$log', '$mdToast', '$http', '$location'];

    function SignupController($log, $mdToast, $http, $location) {
        var vm = this;

        vm.user = {};
        vm.user.login = '';
        vm.user.password = '';
        vm.user.email = '';
        vm.register = register;

        function register() {
            $log.info("signup", vm.user);
            $http.post('/api/users', {
                    username: vm.user.username,
                    email: vm.user.email,
                    password: vm.user.password
                }
            ).success(function (data) {
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Zarejestrowano.')
                            .position("top left")
                            .hideDelay(2000)
                    );
                    $location.path("/");
                })
                .error(function (data) {
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
