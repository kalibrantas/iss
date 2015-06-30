(function() {
	app = angular.module("HomeApp", [ 'ngRoute' ]);

	app.config(function($routeProvider, $locationProvider) {
		$routeProvider.when('/home/createsurvey', {
			templateUrl : '/templates/survey_create',
			controller : 'CreateSurveyController',
		// resolve: {
		// // I will cause a 1 second delay
		// delay: function($q, $timeout) {
		// var delay = $q.defer();
		// $timeout(delay.resolve, 1000);
		// return delay.promise;
		// }
		// }
		}).when('/home/users', {
			templateUrl : '/templates/user_list',
			controller : 'UsersController',
		}).when('/home/users/create', {
			templateUrl : '/templates/user_create',
			controller : 'UsersController',
		})
		.when('/home/surveys', {
			templateUrl : '/templates/survey_list',
			controller : 'ListSurveyController',
		})
		.when('/survey/:idSurvey/', {
			redirectTo:'/surveys'
		})


		// configure html5 to get links working on jsfiddle
		$locationProvider.html5Mode(true);
	});

	app.run(function($rootScope) {

	});

	app.controller("UsersController", function($scope, $http) {
		$scope.user = {};
		$scope.user_list = {};

		$scope.save = function() {
			$http.post("/user/save", $scope.user).success(
					function(data, status) {

					});
		}
		$http.get("/user/getall").success(function(data, status) {
			$scope.user_list = data;
		})
	})

	app.controller("ListSurveyController", function($scope, $http) {
		$scope.survey_list = [];
		$http.get("/survey/getall").success(function(data, status) {
			$scope.survey_list = data;
		})
	})

	app.controller("CreateSurveyController", function($scope, $http) {
		$scope.survey = {};
		$scope.createNewSurvey = function() {
			$http.post("/survey/create", {
				name : $scope.survey.name
			}).success(function() {
			}).error(function() {
			});
		}
	});
})()