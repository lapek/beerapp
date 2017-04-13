(function () {
    'use strict';

    angular
        .module('beerApp')
        .factory('AccountService', AccountService);

    AccountService.$inject = ['$http'];

    function AccountService($http) {
        return {
            getProfile: function () {
                return $http.get('/api/user');
            },
            updateProfile: function (profileData) {
                return $http.put('/api/user', profileData);
            }
        }
    }
})();