(function() {
	var app = angular.module('myApp', []);

	app.factory("questionFactory", function() {
		var a = {};
		a.coba = function() {
			return "ini function";
		};
		return a;
	});

	app.factory("questionService", function($http, $rootScope) {
		question = {};
		question.getQuestion = function() {
			$http.get("data.json").success(function(data, status, header, config) {
				$rootScope.$emit("questionChange", data.question_list);
				return data.question_list;
			}).error(function(data, status) {
				alert(status);
			});
		};

		return question;
	});

	app.controller('cobaController', function($scope) {;
		$scope.data = "aan";
		$scope.aa = function() {
			$scope.data = "mboh";
		};
	}).directive('cobaDirective', function($compile) {
		return {
			link : function(scope, element, attrs) {
				element.css({
					position : 'relative',
					border : '1px solid red',
					backgroundColor : 'lightgrey',
					cursor : 'pointer'
				});
				element.html('<span ng-bind="data">sadsd</span>');
				$compile(element.contents())(scope);
			}
		};
	});
	var questionType = [{
		name : "integer",
		label : "integer"
	}, {
		name : "decimal",
		label : "decimal"
	}, {
		name : "text",
		label : "text"
	}, {
		name : "select_one",
		label : " select one"
	}, {
		name : "select_multiple",
		label : "select multiple"
	}];

	var question_list = {};
	var choice_list = {
		jenis_kelamin : [{
			name : "1",
			label : "laki-laki"
		}, {
			name : "2",
			label : "perempuan"
		}]
	};
	var option_temp = [];
	var options_name_temp = "";
	var min_temp = "";
	var max_temp = "";
	app.controller('NewQuestionController', function($scope, $rootScope, $compile, $filter) {
		$scope.question_types = questionType;
		$scope.qName = "";
		$scope.qLabel = "";
		$scope.qType = "";
		$scope.addQuestion = function() {
			type = $scope.qType.name;
			switch(type) {
			case "select_one":
				{
					choice_list[options_name_temp] = option_temp;
					question_list.push({
						name : $scope.qName,
						type : $scope.qType.name,
						label : $scope.qLabel,
						option : {
							choice : options_name_temp
						}
					});
				};
				break;
			case "select_multiple":
				{
					choice_list[options_name_temp] = option_temp;
					question_list.push({
						name : $scope.qName,
						type : $scope.qType.name,
						label : $scope.qLabel,
						option : {
							choice : options_name_temp,
							min : min_temp,
							max : max_temp
						}
					});
				};
				break;
			case "":
				{

				};
				break;
			}

			angular.element('#myModal').modal('hide');

		};

		$scope.addQuestionOption = function() {
			var e = angular.element("#question_option");
			var clone;

			var choice = $scope.qType.name;
			switch(choice) {
			case'integer':
				{
					var template = document.querySelector('#option_integer');
					var clone = document.importNode(template.content, true);
				}
				break;
			case 'select_one':
				{
					var template = document.querySelector('#option_select_one');
					var clone = document.importNode(template.content, true);
				}
				break;
			case 'select_multiple':
				{
					var template = document.querySelector('#option_select_multiple');
					var clone = document.importNode(template.content, true);
				}
				break;
			}

			e.html(clone);
			$compile(e.contents())($scope);
		};

		$rootScope.$on("editQuestion", function(event, data) {
			q = $filter('filter')(question_list, {
				name : data
			});

			$scope.qName = q[0].name;
			$scope.qLabel = q[0].label;
			$scope.qType = $filter('filter')(questionType,{name:q[0].type})[0];

			$scope.addQuestionOption();
			if (q[0].type == "select_one" || q[0].type == "select_multiple")
				option_temp = choice_list[q[0].option.choice];
			angular.element('#myModal').modal('show');
		});
	});

	app.controller("QuestionController", function($scope, $filter, $rootScope, questionService) {

		$scope.question_list = question_list;
		questionService.getQuestion();

		$rootScope.$on("questionChange", function(event, data) {
			question_list = data;
			$scope.question_list = data;

		});

		$scope.cob = 1;
		$scope.coba = function() {
			cob++;
		};

		$scope.$on("deleteQuestion", function(event, data) {
			$scope.question_list = $filter('filter')($scope.question_list, {
				name : "!" + data
			});
		});

		$scope.del = function() {
			//$scope.question_list = $filter('filter')($scope.question_list,{name:"!B1R1"});
			temp = $scope.question_list;
			temp.shift();
			$scope.question_list = temp;
		};
	}).directive("questionItem", function($compile) {
		return {
			scope : {
				data : "="
			},
			templateUrl : 'my-coba.html',
			link : function(scope, e, attrs) {
				a = "";
				qae = e.find(".question-answer");
				html = "";
				switch(scope.data.type) {
				case "integer":
					{
						ne = document.createElement("div");
						html = angular.element(ne);
						input = angular.element("<input/>", {
							type : "number",
							name : "optionsRadios"
						});
						html.append(input);
					}
					break;
				case "text":
					{
						ne = document.createElement("div");
						html = angular.element(ne);
						input = angular.element("<input/>", {
							type : "text",
							name : "optionsRadios"
						});
						html.append(input);
					}
					break;
				case "select_one":
					{
						choices = choice_list[scope.data.option.choice];
						ne = document.createElement("div");
						html = angular.element(ne);
						for (i in choices) {
							choice = choices[i];
							input = angular.element("<input/>", {
								type : "radio",
								name : "optionsRadios",
								value : choice.name
							});
							label = angular.element("<label/>");
							label.append(input);
							label.append(choice.label);
							div = angular.element("<div/>", {
								class : "radio"
							});
							div.append(label);
							html.append(div);
						}
					}
					break;
				case "select_multiple":
					{
						choices = choice_list[scope.data.option.choice];
						ne = document.createElement("div");
						html = angular.element(ne);
						for (i in choices) {
							choice = choices[i];
							input = angular.element("<input/>", {
								type : "checkbox",
								name : "optionsRadios",
								value : choice.name
							});
							label = angular.element("<label/>");
							label.append(input);
							label.append(choice.label);
							div = angular.element("<div/>", {
								class : "checkbox"
							});
							div.append(label);
							html.append(div);
						}
					}
					break;

				}
				qae.html(html);
				$compile(e.contents())(scope);
			},
			controller : function($scope, $rootScope, $filter) {

				$scope.deleteQuestionItem = function(qName) {
					$scope.$emit("deleteQuestion", qName);
				};
				$scope.editQuestionItem = function(qName) {
					$rootScope.$emit("editQuestion", qName);
				};
			}
		};
	});

	app.controller("QuestionListController", function($scope,$rootScope) {
		$scope.question_list=question_list;
		
		$rootScope.$on("questionChange", function(event, data) {
			question_list = data;
			$scope.question_list = data;

		});
	});

	app.controller("OptionSelectOneController", function($scope, $filter, $rootScope) {

		$scope.options = option_temp;
		$scope.choice_list_name = options_name_temp;
		$scope.name = "";
		$scope.label = "";
		$scope.$watch("choice_list_name", function(nv, ov) {
			options_name_temp = nv;
		});
		$scope.addNewOption = function() {
			option_temp.push({
				name : $scope.name,
				label : $scope.label
			});
			$scope.options = option_temp;
			//option_temp.coba_list.push({name:$scope.name,label:$scope.label});
		};
		$scope.romoveOption = function(data) {
			$scope.options = $filter('filter')($scope.options, '!' + data);
		};
	});

	app.controller("OptionSelectMultipleController", function($scope, $filter, $rootScope) {

		$scope.options = option_temp;
		$scope.choice_list_name = options_name_temp;
		$scope.name = "";
		$scope.label = "";
		$scope.min = "";
		$scope.max = "";
		$scope.$watch("choice_list_name", function(nv, ov) {
			options_name_temp = nv;
		});
		$scope.$watch("min", function(nv, ov) {
			min_temp = nv;
		});
		$scope.$watch("max", function(nv, ov) {
			max_temp = nv;
		});

		$scope.addNewOption = function() {
			option_temp.push({
				name : $scope.name,
				label : $scope.label
			});
			$scope.options = option_temp;
			//option_temp.coba_list.push({name:$scope.name,label:$scope.label});
		};
		$scope.romoveOption = function(data) {
			$scope.options = $filter('filter')($scope.options, '!' + data);
		};
	});
	// app.directive("optionSelectOne", function(){
	// return{
	// compile:function(elem, attrs){
	// a=attrs.info;
	// choice = choice_list[a];
	// elem.html(choice[1].name);
	// }
	// };
	// });
	// app.controller("CobaController", function($scope) {
	// $scope.aa = ["direc", "direcc"];
	// $scope.abc = ["direc1", "direc2", "direc1", "direc2", "direc1"];
	// }).directive("direc", function() {
	// return {
	// restrict : "AEC",
	// scope : {
	// coba : "@"
	// },
	// template : "{{coba}}"
	// };
	// }).directive("direcc", function() {
	// return {
	// restrict : "AEC",
	// template : "direc2"
	// };
	// });

	var createElement = function(element, option) {
		var newElement = document.createElement(element);
		for (att in option) {
			var newAtt = document.createAttribute(att);
			newAtt.value = option[att];
			newElement.setAttributeNode(newAtt);
		}
		return newElement;

	};

	function a() {
		alert("sadsa");
	}

})();

