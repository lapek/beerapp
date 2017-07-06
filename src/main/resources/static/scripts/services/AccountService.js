(function () {
    'use strict';

    angular
        .module('beerApp')
        .factory('AccountService', AccountService);

    AccountService.$inject = ['$http'];

    function AccountService($http) {
        return {
            getProfile: function () {
                return $http.get('/profile');
            },
            updateProfile: function (profileData) {
                return $http.put('/profile', profileData);
            }
        }
    }
})();