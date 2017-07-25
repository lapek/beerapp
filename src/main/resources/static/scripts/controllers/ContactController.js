(function () {
    'use strict';

    angular.module('beerApp')
        .controller('contactController', contactController);

    contactController.$inject = [];

    function contactController() {
        var vm = this;

        vm.email = '';
        vm.message = '';

    }
})();