(function () {
    'use strict';

    angular
        .module('beerApp')
        .config(themeConfig);

    themeConfig.$inject = ['$mdIconProvider', '$mdThemingProvider'];

    function themeConfig($mdIconProvider, $mdThemingProvider) {

        $mdThemingProvider.theme('default')
            .primaryPalette('yellow')
            .accentPalette('indigo');

        $mdThemingProvider.theme('login')
            .primaryPalette('deep-orange')
            .accentPalette('lime');

        $mdIconProvider.defaultIconSet('../styles/mdi.svg');

    }
})();