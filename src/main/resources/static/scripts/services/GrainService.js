(function () {
    'use strict';

    angular
        .module('beerApp')
        .factory('GrainService', GrainService);

    GrainService.$inject = ['$rootScope'];

    function GrainService($rootScope) {
        var grain = [];

        var addList = function (newObj) {
            grain.push(newObj);
        };

        var removeList = function (index) {
            grain.splice(index, 1);
        };

        var getList = function () {
            return grain;
        };

        return {
            addList: addList,
            getList: getList,
            removeList: removeList
        };

    }

})();