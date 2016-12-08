(function () {
    'use strict';

    angular
        .module('beerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider'];

    function stateConfig($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        $urlRouterProvider.otherwise('/home');

        $locationProvider.html5Mode({
            enabled: true,
            requireBase: true
        });

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: '../views/home.html'
            })
            .state('signup', {
                url: '/signup',
                templateUrl: '../views/signup.html'
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
                resolve : {
                    resolvedUser : checkForAuthenticatedUser
                }
            });

        function checkForAuthenticatedUser(AuthService, $state) {
            return AuthService.getCurrentUser().then(function (_user) {
                return _user;
            }, function (_error) {
                $state.go('home');
            })
        }
    }
})();