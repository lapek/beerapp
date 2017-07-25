(function () {
    'use strict';

    angular
        .module('beerApp')
        .config(translateConfig);

    translateConfig.$inject = ['$translateProvider'];

    function translateConfig($translateProvider) {

        $translateProvider.useStaticFilesLoader({
            prefix: '../translations/locale-',
            suffix: '.json'
        });
        $translateProvider.preferredLanguage('en');
        $translateProvider.fallbackLanguage('en');
        $translateProvider.useSanitizeValueStrategy(null);

    }

})();