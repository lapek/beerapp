(function () {
    'use strict';

    angular
        .module('beerApp')
        .factory('LoginService', LoginService);

    LoginService.$inject = ['$resource'];

    function LoginService($resource) {
        return $resource(':action', {},
            {
                authenticate: {
                    method: 'POST',
                    url : '/api/authenticate',
                    params: {'action' : 'authenticate'}
                }
            }
        );
    }

})();