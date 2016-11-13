(function () {
    'use strict';

    angular
        .module('beerApp')
        .config(themeConfig);

    themeConfig.$inject = ['$mdIconProvider', '$mdThemingProvider'];

    function themeConfig($mdIconProvider, $mdThemingProvider) {

        $mdThemingProvider.theme('default').primaryPalette('yellow').accentPalette('blue');
        
        $mdThemingProvider.theme('login').primaryPalette('brown').accentPalette('yellow');

        $mdIconProvider.defaultIconSet('../styles/mdi.svg');

    }
})();