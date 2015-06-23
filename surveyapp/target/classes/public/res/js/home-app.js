/**
 * 
 */

(function() {
	app = angular.module("HomeApp",[]);

	app.run(function($rootScope) {

	});
	
	app.controller("ListSurveyController", function($scope, $http){
		$scope.survey_list=[];
		$http.get("/survey/getall").success(function(data, status){
			$scope.survey_list=data;
		})
	})

	app.controller("CreateNewSurveyController", function($scope,$http) {
		$scope.survey={};
		$scope.createNewSurvey=function(){
			$http.post("/survey/create",{name:$scope.survey.name}).success(function(){}).error(function(){});
		}
	});
})()