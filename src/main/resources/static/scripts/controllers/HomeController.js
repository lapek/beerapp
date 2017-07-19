(function () {
    'use strict';

    angular.module('beerApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$http', '$rootScope', '$auth'];

    function HomeController($scope, $http, $rootScope, $auth) {
        var vm = this;

        vm.lastPublic = {};
        vm.allPublic = [];
        vm.lastUserRecipe = {};

        vm.getAllPublic = getAllPublic;
        vm.getLastPublic = getLastPublic;
        vm.getUserLast = getUserLast;
        vm.init = init;

        function init() {
            vm.getLastPublic();
            //vm.getAllPublic();
            if($auth.isAuthenticated()) vm.getUserLast();
        }

        $scope.isAuthenticated = function() {
            return $auth.isAuthenticated();
        };

        function getAllPublic() {
            $http({
                method: 'GET',
                url: '/api/recipes/list/public',
                skipAuthorization: true
            }).then(function onSuccess(response) {
                vm.allPublic = response.data;
            }, function onError(response) {
                //vm.allPublic = '';
            });
        }

        function getLastPublic() {
            $http({
                method: 'GET',
                url: '/api/recipes/last/public'
                //skipAuthorization: true
            }).then(function onSuccess(response) {
                vm.lastPublic = response.data;
            }, function onError(response) {
                //vm.lastPublic = '';
            });
        }

        function getUserLast() {
            if ($auth.isAuthenticated()) {
                $http({
                    method: 'GET',
                    url: '/api/recipes/last/user'
                }).then(function onSuccess(response) {
                    vm.lastUserRecipe = response.data;
                }, function onError(response) {
                    //
                });
            }
        }

    }
})();