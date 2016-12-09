(function () {
    'use strict';

    angular
        .module('beerApp')
        .factory('AuthService', AuthService);

    AuthService.$inject = ['$rootScope', '$http', '$q', '$localStorage', '$window'];

    function AuthService($rootScope, $http, $q, $localStorage, $window) {
        var authenticate = function (callback) {
            $http.get('/api/users').success(function (data) {
                if (data.name) {
                    $rootScope.currentUser = data.name;
                    $rootScope.authenticated = true;
                    $window.localStorage.setItem('CurrentUser', JSON.stringify($rootScope.currentUser));
                } else {
                    $rootScope.currentUser = null;
                    $rootScope.authenticated = false;
                    $window.localStorage.setItem('CurrentUser', null);
                }
                callback && callback();
            }).error(function () {
                $rootScope.currentUser = null;
                $rootScope.authenticated = false;
                $window.localStorage.setItem('CurrentUser', null);
                callback && callback();
            });
        };

        var getCurrentUser = function () {
            var CurrentUser = $window.localStorage.getItem('CurrentUser');
            CurrentUser = CurrentUser && JSON.parse(CurrentUser);

            if (CurrentUser) {
                $rootScope.currentUser = CurrentUser;
                $rootScope.authenticated = true;
                return $q.when(CurrentUser);
            } else {
                $rootScope.currentUser = null;
                $rootScope.authenticated = false;
                return $q.reject("NO USER");
            }

            // if ($rootScope.currentUser != undefined || $rootScope.currentUser != null) {
            //     return $q.when($rootScope.currentUser);
            // } else {
            //     return $q.reject("NO USER");
            // }
        };

        var isAuthenticated = function() {
            var CurrentUser = $window.localStorage.getItem('CurrentUser').toString();
            if((CurrentUser !== null) && (CurrentUser !== undefined) && (CurrentUser.toString() != 'null')){
                $rootScope.authenticated = true;
                $rootScope.currentUser = $window.localStorage.getItem('CurrentUser').toString();
                return true;
            } else {
                $rootScope.authenticated = false;
                $rootScope.currentUser = null;
                return false;
            }
        };

        return {
            authenticate: authenticate,
            isAuthenticated: isAuthenticated,
            getCurrentUser: getCurrentUser
        };

    }

})();