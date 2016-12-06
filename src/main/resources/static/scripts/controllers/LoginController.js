(function () {
    'use strict';

    angular.module('beerApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$mdDialog',
        '$mdToast',
        '$log', '$rootScope',
        '$scope', '$http',
        '$state', '$cookieStore',
        '$cookies', '$location',
        '$q', '$resource'
    ];

    function LoginController($mdDialog, $mdToast, $log, $rootScope, $scope, $http, $state, $cookieStore, $cookies, $location, $q, $resource, LoginService, appConfiguration) {
        var vm = this;

        vm.cancel = cancel;
        vm.login = login;
        vm.authenticate = authenticate;
        vm.credentials = {};
        vm.credentials.username = '';
        vm.credentials.password = '';

        function cancel() {
            $mdDialog.hide();
        }

        function login($event) {
            $log.info('Logging in...', vm.credentials);
            $http.post('login', vm.credentials, {
                headers: {
                    "content-type": 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                }
            })
                .success(function (data) {
                    vm.authenticate(function () {
                        if ($rootScope.authenticated) {
                            $state.go("home");
                            successToast();
                            cancel();
                        } else {
                            $location.path("/login");
                            errorToast();
                        }
                    });
                }).error(function (data) {
                errorToast();
                $rootScope.authenticated = false;

            });
        }

        function authenticate(callback) {
            $http.get('/api/users').success(function (data) {
                if (data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }).error(function () {
                $rootScope.authenticated = false;
                callback && callback();
            });
        }

        function errorToast() {
            $mdToast.show(
                $mdToast.simple()
                    .textContent('Problem z logowaniem, spróbuj ponownie.')
                    .position("top left")
                    .hideDelay(10000)
            );
        }

        function successToast() {
            $mdToast.show(
                $mdToast.simple()
                    .textContent('Zalogowano.')
                    .position("top left")
                    .hideDelay(2000)
            );
        }

        // function login($event) {
        //     if (vm.user.name != null || vm.user.pass != null) {
        //         console.log("Trying login...");
        //         new Login({username: vm.user.name, password: vm.user.pass},
        //             function (data, headers) {
        //                 $localStorage.user = data.user;
        //                 $localStorage.authToken = headers['x-auth-token'];
        //                 $http.defaults.headers.common['x-auth-token'] = headers['x-auth-token'];
        //                 $location.path("/");
        //             }, function (error) {
        //                 console.log(error);
        //             });
        //     }
        // }

        // function login($event) {
        //     console.log("Trying login...");
        //     if (vm.user.name != null || vm.user.pass != null) {
        //
        //     } else {
        //         console.log("No username or/and password.");
        //     }
        // }
    }
})();