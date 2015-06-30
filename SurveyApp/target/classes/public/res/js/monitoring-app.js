(function(){
	var app = angular.module("SurveyMonitoringApp",[]);
	
	app.controller('RespondentMonitorController', function($scope, $interval, $http){
		$scope.respondents=[];
		$interval(function(){
			$http.post("surveymonitoring/getrespondents").success(function(data,status){
				$scope.respondents=data;
			}).error(function(data,status){
				
			});
		},5000);
	});
})()