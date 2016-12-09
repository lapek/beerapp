(function () {
    'use strict';

    angular.module('beerApp')
        .controller('myAccountController', myAccountController);

    myAccountController.$inject = ['$rootScope'];

    function myAccountController($rootScope) {
        var vm = this;

        vm.userdata = {};

        function init() {
            vm.getUserData();
        }

        function getUserData() {
            $http({
                method: 'GET',
                url: 'api/users/find',
                params: {
                    username: $rootScope.currentUser
                }
            }).success(function (response) {
                vm.userdata = response;
            }).error(function (response) {
                //
            });
        }
    }
})();