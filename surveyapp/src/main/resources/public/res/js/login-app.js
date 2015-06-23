(function() {
	var app = angular.module("LoginApp", []);

	app
			.config(function($locationProvider,$httpProvider ) {
				$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
				 $locationProvider.html5Mode({
					  enabled: true,
					  requireBase: false
					});
			});

	app.controller("LoginController", function($scope, $rootScope, $http,
			$location, $window) {
		$scope.username = "u";
		$scope.password = "p";
		$scope.rememberme = false;

		var authenticate = function(credentials, callback) {

			var headers = credentials ? {
				authorization : "Basic "
						+ btoa(credentials.username + ":"
								+ credentials.password)
			} : {};

			$http.get('user', {
				headers : headers
			}).success(function(data) {
				if (data.name) {
					$rootScope.authenticated = true;
				} else {
					$rootScope.authenticated = false;
				}
				callback && callback();
			}).error(function() {
				$rootScope.authenticated = false;
				callback && callback();
			});

		}

	//	authenticate();
		$scope.credentials = {};
		$scope.submit = function() {
			authenticate($scope.credentials, function() {
				if ($rootScope.authenticated) {
					$window.location.href="/questionnairebuilder/"
					$scope.error = false;
				} else {
					$location.path("login");
					$scope.error = true;
				}
			});
		}

		$scope.logout = function() {
			$http.post('logout', {}).success(function() {
				$rootScope.authenticated = false;
				$location.path("/");
			}).error(function(data) {
				$rootScope.authenticated = false;
			});
		}

	});
})()