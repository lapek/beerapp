(function () {
    'use strict';

    angular.module('beerApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$window', '$http', '$rootScope', '$interval', 'AuthService'];

    function HomeController($scope, $window, $http, $rootScope, $interval, AuthService) {
        var vm = this;

        vm.lastPublic = '';
        vm.allPublic = '';
        vm.lastUserRecipe = '';

        vm.getAllPublic = getAllPublic;
        vm.getLastPublic = getLastPublic;
        vm.getUserLast = getUserLast;
        vm.init = init;

        function init() {
            vm.getLastPublic();
            vm.getAllPublic();
            vm.getUserLast();
        }

        function getAllPublic() {
            $http({
                method: 'GET',
                url: '/api/recipes/list/public'
            }).then(function onSuccess(response) {
                vm.allPublic = response;
            }, function onError(response) {
                //vm.allPublic = '';
            });
        }

        function getLastPublic() {
            $http({
                method: 'GET',
                url: '/api/recipes/last/public'
            }).then(function onSuccess(response) {
                vm.lastPublic = response;
            }, function onError(response) {
                //vm.lastPublic = '';
            });
        }

        function getUserLast() {
            $http({
                method: 'GET',
                url: '/api/recipes/last/user',
                params: {
                    author: $rootScope.currentUser
                }
            }).then(function onSuccess(response) {
                vm.lastUserRecipe = response;
            }, function onError(response) {
                //
            });
        }


        $interval(vm.getLastPublic, 30000);
        //$interval(vm.getUserLast, 30000);

    }
})();