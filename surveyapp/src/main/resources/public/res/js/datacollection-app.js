/**
 * 
 */
(function() {
	var app = angular.module('myApp', []);
	
	app.controller("RespondentMonitorController", function($scope, $interval, $http){
		$scope.respondents=[];
		$interval(function(){
			$http.post("getrespondents").success(function(data,status){
				$scope.respondents=data;
			}).error(function(data,status){
				
			});
		},1000);
	});
})()