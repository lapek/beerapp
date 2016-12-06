(function () {
    'use strict';

    angular
        .module('beerApp')
        .constant('VERSION', "0.0.1-SNAPSHOT")
        .constant('DEBUG_INFO_ENABLED', true)
        .constant('API_URL', 'http://localhost:8080/api/');
})();
