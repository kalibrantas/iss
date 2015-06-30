/**
 * 
 */
(function() {
	var app = angular.module('QuestionnaireBuilderApp', [ 'angularSpinner', 'ngAnimate',
			'ui.sortable' ])
	app.directive("comboAutocomplete", function($http, $filter, $timeout) {
		return {
			templateUrl : "/comboAutocomplete.html",
			transclude : true,
			scope : {
				'cadata' : "="
			},
			link : function(scope, elm, attr) {

				elm.find(".ac-list").css("width",
						elm.find(".combo-autocomplete").width() + "px")
			},
			controller : function($scope) {
				$scope.searchText = "";
				$scope.data = [ "asdsa", "asdsadsa", "asdasdsa" ];
				$scope.dataShow = [];
				$scope.showList = false;
				$scope.$watch('cadata.choice', function(nv, ov) {
					$scope.dataShow = $filter('filter')($scope.data, nv);
					if (nv != ov && nv != '')
						$scope.showList = true;
					else
						$scope.showList = false;
				});
				$scope.select = function(select) {
					$scope.searchText = select;
					$scope.showList = false;
				}
				$scope.onBlur = function() {
					$timeout(function() {
						$scope.showList = false;
					}, 200);
				}
				$scope.toggleList = function() {
					if ($scope.showList)
						$scope.showList = false;
					else
						$scope.showList = true;
				}
			}
		}
	});

	app.directive('usSpinner', function($http, $rootScope) {
		return {
			link : function(scope, elm, attrs) {
				$rootScope.spinnerActive = false;
				scope.isLoading = function() {
					return $http.pendingRequests.length > 0;
				};

				scope.$watch(scope.isLoading, function(loading) {
					$rootScope.spinnerActive = loading;
					if (loading) {
						elm.removeClass('ng-hide');
					} else {
						elm.addClass('ng-hide');
					}
				});
			}
		};

	});

	app.directive("questionItem", function($compile) {
		return {
			scope : {
				qidata : "=",
				no : "="

			},
			templateUrl : 'questionnairebuilder/templates/qb_question_item',
			link : function(scope, e, attrs) {
				a = "";
				qae = e.find(".question-answer");
				html = "";
				switch (scope.qidata.type) {
				case "integer": {
					ne = document.createElement("div");
					html = angular.element(ne);
					input = angular.element("<input/>", {
						type : "number",
						name : "optionsRadios"
					});
					html.append(input);
				}
					break;
				case "text": {
					ne = document.createElement("div");
					html = angular.element(ne);
					input = angular.element("<input/>", {
						type : "text",
						name : "optionsRadios"
					});
					html.append(input);
				}
					break;
				case "select_one": {
					choices = choice_list[scope.qidata.choice];
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
				case "select_multiple": {
					choices = choice_list[scope.qidata.choice];
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
				case "group": {

					ne = document.createElement("div");
					html = angular.element(ne);
					ul = angular.element("<ul/>", {
						'ui-sortable' : "sortOption",
						'ng-model' : "qidata.children",
						class : "list-group"
					});
					li = angular.element("<li/>", {
						'question-item' : '',
						'qidata' : "question_item",
						no : "$index+1",
						'ng-repeat' : "question_item in qidata.children",

						class : "list-group-item no-padding"
					});
					ul.html(li);
					html.append(ul);
				}

				}
				qae.html(html);
				$compile(qae.contents())(scope);
			},
			controller : function($scope, $rootScope, $filter) {
				$scope.selected = false;
				$scope.deleteQuestionItem = function(qName) {
					// $scope.$emit("deleteQuestion", qName);
					$rootScope.deleteQuestion(qName);
				};
				$scope.editQuestionItem = function(qName) {
					// $rootScope.$emit("editQuestion", qName);
					$rootScope.coba();
				};

				$scope.select = function(qName) {
					$rootScope.selectedQuestion = qName;
					$scope.selected = true;
				}

				// ini berat
				$rootScope.$watch('selectedQuestion', function(nv, ov) {
					if ($scope.qidata.name == $rootScope.selectedQuestion)
						$scope.selected = true;
					else
						$scope.selected = false;
				})
			}
		};
	});

	var questionType = [ {
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
	} ];

	var question_list = {};
	var choice_list = {
		jenis_kelamin : [ {
			name : "1",
			label : "laki-laki"
		}, {
			name : "2",
			label : "perempuan"
		} ]
	};
	var option_temp = [];
	var options_name_temp = "";
	var min_temp = "";
	var max_temp = "";

	app.run(function($rootScope, $filter,$http) {
		$rootScope.selectedQuestion = '';
		$rootScope.question_list = [];
		$rootScope.choice_list = choice_list;
		//$rootScope.getQuestion = function() {
			$http.get("/get").success(
					function(data, status, header, config) {
						$rootScope.question_list = data.questionList;
						//return data.question_list;
					}).error(function(data, status) {
				alert(status);
			});
		//};
		$rootScope.deleteQuestion = function(qName) {
			$rootScope.question_list = $filter('filter')(
					$rootScope.question_list, {
						name : "!" + qName
					});
		}
		$rootScope.showQuestionPanel = function() {
			$rootScope.addQuestionPanel = true;
		}
		$rootScope.addQuestion = function(qTypeNo) {
			qTypes = [ "text", "number", "select_one", "select_multiple",
					"group" ];
			sq = $rootScope.getSelectedQuestion();
			if (sq != undefined) {
				if (sq.type == "group") {
					sq.children.push(createQuestion(sq.name + "_question"
							+ (sq.children.length + 1), qTypes[qTypeNo]));
				} else {
					$rootScope.question_list.push(createQuestion("question"
							+ ($rootScope.question_list.length + 1),
							qTypes[qTypeNo]));
				}
			}else{
				$rootScope.question_list.push(createQuestion("question"
						+ ($rootScope.question_list.length + 1),
						qTypes[qTypeNo]));
			}
			$rootScope.selectedQuestion = "question"
					+ $rootScope.question_list.length;
			console.log($rootScope.selectedQuestion)
			$rootScope.addQuestionPanel = false;
		}

		$rootScope.getSelectedQuestion = function() {
			return getQuestion($rootScope.selectedQuestion,
					$rootScope.question_list);
		}

		$rootScope.getQuestion = function(qName) {
			return getQuestion(qName, $rootScope.question_list);
		}

	});

	function createQuestion(qName, qType) {
		if (qType == "group") {
			return {
				name : qName,
				type : qType,
				children : []
			};
		} else {
			return {
				name : qName,
				type : qType,
			};
		}
	}

	function getQuestion(qName, question_list) {
		for (no in question_list) {
			if (question_list[no].type == "group") {
				if (question_list[no].name == qName) {
					return question_list[no];
				} else if (question_list[no].children.length != 0) {
					res = getQuestion(qName, question_list[no].children);
					if (res != undefined)
						return res;
				} else {

				}
			} else {
				if (question_list[no].name == qName)
					return question_list[no];
			}
		}
	}

	app.factory("questionService", function($http, $rootScope) {
		question = {};
		

		return question;
	});

	app.controller("QuestionController", function($scope, $filter, $rootScope,
			questionService) {
		$rootScope.getQuestion();
		$scope.sortOption = {
			update : function(e, ui) {
			},
			stop : function(e, ui) {
				// $rootScope.selectedQuestion = ui.item.index();
			}
		}

	});

	app.controller("AddQuestionController",
			function($scope, $http, $rootScope) {
				$scope.addTextQuestion = function() {
					$rootScope.addQuestionPanel = false;
				}
				$rootScope.addQuestionPanel = true;

			});

	app.controller("QuestionPropertiesController",
			function($scope, $rootScope) {
				$scope.data = {};
				$rootScope.$watch('selectedQuestion', function(nv, ov) {
					$scope.data = $rootScope.getQuestion(nv)
					$rootScope.addQuestionPanel = false;
					console.log($rootScope.question_list);
					console.log($rootScope.choice_list);
				});

			});

	app.controller("AddNewOptionsController", function($scope, $rootScope) {
		$scope.optionName = "";
		$scope.optionValue = "";
		$scope.optionLabel = "";
		dirty = false;
		$scope.save = function() {
			for (item in choice_list) {
				if ($scope.optionName == item) {
					alert("tidak boleh sama");
					dirty = true;
				}
			}
			if (!dirty) {
				valueArr = $scope.optionValue.split("\n");
				labelArr = $scope.optionLabel.split("\n");
				if (valueArr.length != labelArr.length)
					alert("error number of value must equal with label");
				else {
					newOption = [];
					for (i = 0; i < valueArr.length; i++) {
						newOption.push({
							name : valueArr[i],
							label : labelArr[i]
						})
					}
					choice_list[$scope.optionName] = newOption;
					angular.element("#modalAddOptions").modal('hide');
					reset();
					console.log(choice_list)
				}
			}
		};
		$scope.cancel = function() {
			reset();
		}
		reset = function() {
			$scope.optionName = "";
			$scope.optionValue = "";
			$scope.optionLabel = "";
		}

	})

	app.controller("QuestionnaireBuilderActionMenu", function($scope, $rootScope,$http) {
		$scope.save = function() {
			$http.post('/save', $rootScope.question_list).success(function(data, status) {
				console.log(data);
			}).error(function(data, status) {

			});
		};
	});

})()