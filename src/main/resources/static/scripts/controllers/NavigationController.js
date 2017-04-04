(function () {
    'use strict';

    angular.module('beerApp')
        .controller('NavigationController', NavigationController);

    NavigationController.$inject = ['$mdDialog', '$mdToast', '$window', '$scope', '$interval', '$http', '$rootScope', '$state', 'AuthService'];

    function NavigationController($mdDialog, $mdToast, $window, $scope, $interval, $http, $rootScope, $state, AuthService) {
        var vm = this;

        vm.showLogin = showLogin;
        vm.openMenu = openMenu;
        vm.logout = logout;

        // function checkAuthenticated() {
        //     if(AuthService.isAuthenticated() == true){
        //         $rootScope.authenticated = true;
        //         $rootScope.currentUser = $window.localStorage.getItem('CurrentUser').toString();
        //     } else {
        //         $rootScope.authenticated = false;
        //         $rootScope.currentUser = null;
        //     }
        // }

        function openMenu($mdOpenMenu, $event) {
            vm.originatorEv = $event;
            $mdOpenMenu($event);
        }

        function showLogin($event) {
            if ($rootScope.authenticated != true) {
                $mdDialog.show({
                    templateUrl: '../../views/login.html',
                    parent: angular.element(document.body),
                    targetEvent: $event,
                    clickOutsideToClose: true,
                    fullscreen: $scope.customFullscreen
                });
            }
        }

        function logout() {
            console.log('Loging out...');
            $http.post('logout', {}).then(function onSuccess(response) {
                $rootScope.authenticated = false;
                $rootScope.currentUser = null;
                window.localStorage.setItem('CurrentUser', null);
                $state.go("home");
            }, function onError(response) {
                $rootScope.authenticated = false;
                $rootScope.currentUser = null;
                window.localStorage.setItem('CurrentUser', null);
                $state.go("home");
            });
            $mdToast.show(
                $mdToast.simple()
                    .textContent('Wylogowano.')
                    .position("top left")
                    .hideDelay(2000)
            );
        }

    }
})();