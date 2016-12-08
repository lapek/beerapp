(function () {
    'use strict';

    angular.module('beerApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state', '$timeout', 'AuthService'];

    function HomeController($scope, $state, $timeout, AuthService) {
        var vm = this;
        
    }
})();