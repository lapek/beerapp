(function () {
    "use strict";

    angular.module('beerApp')
        .controller('AnonHomeController', AnonHomeController);

    AnonHomeController.$inject = ['$http'];

    function AnonHomeController($http) {
        var vm = this;

        vm.newestPublicRecipes = [];

        vm.$onInit = onInit;

        function onInit() {
            vm.newestPublicRecipes = [
                {
                    "name": 'Teraz PIPA',
                    style: "India Pale Ale",
                    author: "anon"
                },
                {
                    "name": 'Black IPA',
                    style: "Black India Pale Ale",
                    author: "Tygrysek 12"
                },
                {
                    "name": 'Mountain Flower',
                    style: "Experimental",
                    author: "beermaniac"
                }
            ];
        }


    }
})();