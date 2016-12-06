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
                templateUrl: '../views/home.html',
                access: {
                    loginRequired: false
                }
            })
            .state('signup', {
                url: '/signup',
                templateUrl: '../views/signup.html',
                access: {
                    loginRequired: false
                }
            })
            .state('contact', {
                url: '/contact',
                templateUrl: '../views/contact.html',
                access: {
                    loginRequired: false
                }
            })
            .state('help', {
                url: '/help',
                templateUrl: '../views/help.html',
                access: {
                    loginRequired: false
                }
            })
            .state('newRecipe', {
                url: '/newRecipe',
                templateUrl: '../views/newRecipe.html',
                access: {
                    loginRequired: true
                }
            });
    }
})();