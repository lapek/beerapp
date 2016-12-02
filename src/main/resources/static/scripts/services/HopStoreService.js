(function () {
    'use strict';

    angular
        .module('beerApp')
        .factory('HopStoreService', HopStoreService);

    HopStoreService.$inject = ['$rootScope'];

    function HopStoreService($rootScope) {
        var hopStore = [];

        var addList = function (newObj) {
            hopStore.push(newObj);
        };

        var removeList = function (index) {
            hopStore.splice(index, 1);
        };

        var getList = function () {
            return hopStore;
        };

        return {
            addList: addList,
            getList: getList,
            removeList: removeList
        };
    }

})();