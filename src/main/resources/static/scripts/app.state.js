(function () {
    'use strict';

    angular
        .module('beerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$provide', '$httpProvider', '$authProvider', 'cfpLoadingBarProvider'];

    function stateConfig($stateProvider, $urlRouterProvider, $locationProvider, $provide, $httpProvider, $authProvider, cfpLoadingBarProvider) {

        cfpLoadingBarProvider.includeSpinner = false;

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

        $urlRouterProvider.otherwise('/home');

        $locationProvider.html5Mode({
            enabled: true,
            requireBase: true
        });

        $provide.factory('ErrorInterceptor', ['$q', function ($q) {
            return {
                responseError: function (rejection) {
                    console.error(rejection.data.code + ': ' + rejection.data.message);
                    return $q.reject(rejection);
                }
            };
        }]);

        $httpProvider.interceptors.push('ErrorInterceptor');

        $authProvider.baseUrl = '/';
        $authProvider.loginUrl = '/auth/login';
        $authProvider.signupUrl = '/auth/signup';
        $authProvider.tokenName = 'token';
        $authProvider.tokenType = 'Bearer';
        $authProvider.tokenHeader = 'Authorization';
        $authProvider.tokenPrefix = 'Bearer';
        $authProvider.storageType = 'sessionStorage';
        $authProvider.loginOnSignup = false;
        $authProvider.tokenRoot = null;

        var skipIfLoggedIn = ['$q', '$auth', function ($q, $auth) {
            var deferred = $q.defer();
            if ($auth.isAuthenticated()) {
                deferred.reject();
            } else {
                deferred.resolve();
            }
            return deferred.promise;
        }];

        var loginRequired = ['$q', '$location', '$auth', function ($q, $state, $auth) {
            var deferred = $q.defer();
            if ($auth.isAuthenticated()) {
                deferred.resolve();
            } else {
                $state.go('app.anon.home');
            }
            return deferred.promise;
        }];

        $stateProvider
            .state('app', {
                abstract: true,
                template: '<ui-view/>'
            })
            // -- anon states --
            .state('app.anon', {
                abstract: true,
                views: {
                    '@': {
                        templateUrl: '../views/layout.html'
                    },
                    'navbar@app.anon': {
                        templateUrl: '../views/anon/navbar.html'
                    },
                    'footer@app.anon': {
                        templateUrl: '../views/anon/footer.html'
                    }
                }
            })
            .state('app.anon.home', {
                url: '/',
                views: {
                    'content@app.anon': {
                        templateUrl: '../views/anon/home.html'
                    }
                },
                resolve: {
                    skipIfLoggedIn: skipIfLoggedIn
                }
            })
            .state('app.anon.signup', {
                url: '/signup',
                views: {
                    'content@app.anon': {
                        templateUrl: '../views/anon/signup.html'
                    }
                },
                resolve: {
                    skipIfLoggedIn: skipIfLoggedIn
                }
            })
            .state('app.anon.login', {
                url: '/login',
                views: {
                    'content@app.anon': {
                        templateUrl: '../views/anon/login.html'
                    }
                },
                resolve: {
                    skipIfLoggedIn: skipIfLoggedIn
                }
            })
            .state('app.anon.contact', {
                url: '/contact',
                views: {
                    'content@app.anon': {
                        templateUrl: '../views/anon/contact.html'
                    }
                }
            })
            .state('app.anon.help', {
                url: '/help',
                views: {
                    'content@app.anon': {
                        templateUrl: '../views/help.html'
                    }
                }
            })
            // -- user states --
            .state('app.user', {
                abstract: true,
                views: {
                    '@': {
                        templateUrl: '../views/layout-side.html'
                    },
                    'navbar@app.user': {
                        templateUrl: '../views/user/navbar.html'
                    },
                    'sidebar@app.user': {
                        templateUrl: '../views/user/sidebar.html'
                    }
                }
            })
            .state('app.user.home', {
                url: '/home',
                views: {
                    'content@app.user': {
                        templateUrl: '../views/user/home.html'
                    }
                },
                resolve: {
                    loginRequired: loginRequired
                }
            })
            .state('app.user.newRecipe', {
                url: '/newRecipe',
                views: {
                    'content@app.user': {
                        templateUrl: '../views/user/newRecipe.html'
                    }
                },
                resolve: {
                    loginRequired: loginRequired
                }
            })
            .state('app.user.myAccount', {
                url: '/myAccount',
                views: {
                    'content@app.user': {
                        templateUrl: '../views/user/myAccount.html'
                    }
                },
                resolve: {
                    loginRequired: loginRequired
                }
            })
            .state('app.user.myRecipes', {
                url: '/myRecipes',
                views: {
                    'content@app.user': {
                        templateUrl: '../views/user/myRecipes.html'
                    }
                },
                resolve: {
                    loginRequired: loginRequired
                }
            });
    }
})();