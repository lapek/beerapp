(function () {
    'use strict';

    angular.module('beerApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$mdDialog', '$log', '$scope'];

    function LoginController($mdDialog, $log, $scope) {
        var vm = this;

        vm.cancel = cancel;
        vm.login = login;
        vm.user = {};

        function cancel() {
            console.log('close dialog');
            $mdDialog.hide();
        }

        function login($event) {
            console.log("Trying login...");
            if (vm.user.name != null || vm.user.pass != null) {

            } else {
                console.log("No username or/and password.");
            }
        }
    }
})();