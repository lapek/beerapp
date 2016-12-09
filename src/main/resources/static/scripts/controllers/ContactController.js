(function () {
    'use strict';

    angular.module('beerApp')
        .controller('ContactController', ContactController);

    ContactController.$inject = [];

    function ContactController() {
        var vm = this;

        vm.email = '';
        vm.message = {};

    }
})();