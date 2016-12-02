(function () {
    'use strict';

    angular
        .module('beerApp')
        .factory('MashingService', MashingService);

    MashingService.$inject = ['$rootScope'];

    function MashingService($rootScope) {
        var mashing = [];

        var addList = function (newObj) {
            mashing.push(newObj);
        };

        var removeList = function (index) {
            mashing.splice(index, 1);
        };

        var getList = function () {
            return mashing;
        };

        return {
            addList: addList,
            getList: getList,
            removeList: removeList
        };
    }

})();