(function(){
	var app=angular.module("SurveyAdminApp",['ngRoute', 'QuestionnaireBuilderApp', 'SurveyMonitoringApp']);
	
	app.config(function($routeProvider, $locationProvider){
		$routeProvider.when('/survey/:idSurvey/questionnairebuilder', {
			templateUrl : '/questionnairebuilder/templates/main',
			controller : 'Main',
		})
		.when('/survey/:idSurvey/surveymonitoring', {
			templateUrl : '/surveymonitoring/templates/main',
			controller : 'RespondentMonitorController',
		});
		
		$locationProvider.html5Mode(true);
	});
	app.run(function($rootScope, $location){
		$rootScope.location=$location;
	});
	
	app.controller("Main", function($scope,$routeParams){
	});
})()