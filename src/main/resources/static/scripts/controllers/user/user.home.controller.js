(function () {
    'use strict';

    angular.module('beerApp')
        .controller('UserHomeController', UserHomeController);

    UserHomeController.$inject = ['$http', '$rootScope', '$auth'];

    function UserHomeController($http, $rootScope, $auth) {
        var vm = this;

        vm.lastUserRecipe = {};

        vm.getUserLast = getUserLast;
        vm.init = init;

        function init() {

        }

        function getUserLast() {
            if ($auth.isAuthenticated()) {
                $http({
                    method: 'GET',
                    url: '/api/recipes/last/user'
                }).then(function onSuccess(response) {
                    vm.lastUserRecipe = response.data;
                }, function onError(response) {
                    vm.lastUserRecipe = null;
                });
            }
        }

    }
})();