var mainApp = angular.module('beerApp', [
    'ui.router',
    'ngMaterial',
    //'ngMessages'
]);

mainApp.config(function ($stateProvider, $urlRouterProvider, $mdThemingProvider, $mdIconProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: '../views/home.html'
        })
        .state('signup', {
            url: '/signup',
            templateUrl: '../views/signup.html'
        });
    
    $mdThemingProvider.theme('default')
        .primaryPalette('yellow')
        .accentPalette('blue');

    $mdThemingProvider.theme('login')
        .primaryPalette('brown')
        .accentPalette('yellow');

    $mdIconProvider
        .defaultIconSet('../mdi.svg')

});

mainApp.controller('NavigationController', function ($mdDialog, $scope) {
    var originatorEv;

    this.openMenu = function ($mdOpenMenu, ev) {
        originatorEv = ev;
        $mdOpenMenu(ev);
    };

    this.showLogin = function (ev) {
        $mdDialog.show({
            //controller: LoginController,
            templateUrl: '../views/login.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true,
            fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
        });
    };

});

mainApp.controller('LoginController', function ($mdDialog, $scope) {
    $scope.cancel = function () {
        $mdDialog.hide();
    };

    this.login = function (ev) {

    };
});

mainApp.controller('SignupController', function () {

});
