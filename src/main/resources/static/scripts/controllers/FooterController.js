(function () {
    'use strict';

    angular.module('beerApp')
        .controller('footerController', footerController);

    footerController.$inject = ['$translate'];

    function footerController($translate) {
        var vm = this;
        var originatorEv;

        vm.openMenu = openMenu;
        vm.changeLanguage = changeLanguage;

        function openMenu($mdMenu, $event) {
            originatorEv = $event;
            $mdMenu.open($event);
        }

        function changeLanguage(langKey) {
            $translate.use(langKey);
        }

    }
})();