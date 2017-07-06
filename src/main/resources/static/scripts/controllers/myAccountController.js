(function () {
    'use strict';

    angular.module('beerApp')
        .controller('myAccountController', myAccountController);

    myAccountController.$inject = ['AccountService'];

    function myAccountController(AccountService) {
        var vm = this;

        vm.userdata = {};

        function init() {
            getUserData();
        }

        function getUserData() {
            AccountService.getProfile()
                .then(function successCallback(response) {
                    console.log(response);
                    vm.userdata = response.data;
                }, function errorCallback(response) {
                    console.log('Get profile error: ', response)
                });
        }

        init();
    }
})();