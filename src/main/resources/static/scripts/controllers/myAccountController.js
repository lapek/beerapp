(function () {
    'use strict';

    angular.module('beerApp')
        .controller('myAccountController', myAccountController);

    myAccountController.$inject = ['$http'];

    function myAccountController($http) {
        var vm = this;

        vm.userdata = {};

        function init() {
            vm.getUserData();
        }

        function getUserData() {
            $http.get('/api/user');
        }
    }
})();