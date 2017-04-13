(function () {
    'use strict';

    angular
        .module('beerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$provide', '$httpProvider', '$authProvider'];

    function stateConfig($stateProvider, $urlRouterProvider, $locationProvider, $provide, $httpProvider, $authProvider) {

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        $urlRouterProvider.otherwise('/home');

        $locationProvider.html5Mode({
            enabled: true,
            requireBase: true
        });

        $provide.factory('ErrorInterceptor', ['$q', function ($q) {
            return {
                responseError: function (rejection) {
                    console.error(rejection.data.code + ': ' + rejection.data.message);
                    return $q.reject(rejection);
                }
            };
        }]);

        $httpProvider.interceptors.push('ErrorInterceptor');

        $authProvider.baseUrl = '/';
        $authProvider.loginUrl = '/auth/login';
        $authProvider.signupUrl = '/auth/signup';
        $authProvider.tokenName = 'token';
        $authProvider.tokenType = 'Bearer';
        $authProvider.tokenHeader = 'Authorization';
        $authProvider.tokenPrefix = 'Bearer';
        $authProvider.storageType = 'sessionStorage';
        $authProvider.loginOnSignup = false;
        $authProvider.tokenRoot = null;

        var skipIfLoggedIn = ['$q', '$auth', function ($q, $auth) {
            var deferred = $q.defer();
            if ($auth.isAuthenticated()) {
                deferred.reject();
            } else {
                deferred.resolve();
            }
            return deferred.promise;
        }];

        var loginRequired = ['$q', '$location', '$auth', function ($q, $location, $auth) {
            var deferred = $q.defer();
            if ($auth.isAuthenticated()) {
                deferred.resolve();
            } else {
                $location.path('/login');
            }
            return deferred.promise;
        }];

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: '../views/home.html'
            })
            .state('signup', {
                url: '/signup',
                templateUrl: '../views/signup.html',
                resolve: {
                    skipIfLoggedIn: skipIfLoggedIn
                }
            })
            .state('contact', {
                url: '/contact',
                templateUrl: '../views/contact.html'
            })
            .state('help', {
                url: '/help',
                templateUrl: '../views/help.html'
            })
            .state('newRecipe', {
                url: '/newRecipe',
                templateUrl: '../views/newRecipe.html',
                resolve: {
                    loginRequired: loginRequired
                }
            })
            .state('myAccount', {
                url: '/myAccount',
                templateUrl: '../views/myAccount.html',
                resolve: {
                    loginRequired: loginRequired
                }
            })
            .state('myRecipes', {
                url: '/myRecipes',
                templateUrl: '../views/myRecipes.html',
                resolve: {
                    loginRequired: loginRequired
                }
            });
    }
})();