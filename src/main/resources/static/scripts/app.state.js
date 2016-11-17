(function () {
    'use strict';

    angular
        .module('beerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider'];

    function stateConfig($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {

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
            .state('newRecipe', {
                url: '/newRecipe',
                templateUrl: '../views/newRecipe.html'
            });
    }
})();