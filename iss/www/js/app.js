(function () {
    var app = angular.module('issMobile', ['ionic']);

    app.config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
                .state('app', {
                    url: "/app",
                    abstract: true,
                    templateUrl: 'templates/index.html',
                })

                .state('login', {
                    url: "/login",
                    templateUrl: "templates/login.html",
                })
                .state('home', {
                    url: "/home",
                    abstract: true,
                    templateUrl: "templates/home.html",
                })
                .state('home.questionnairelist', {
                    url: '/questionnairelist',
                    views: {
                        'menuContent': {
                            templateUrl: 'templates/questionnairelist.html',
                            controller: 'ListQuestionController'
                        }
                    }
                })
                .state('questionnaire', {
                    url: "/questionnaire",
                    abstract: true,
                    templateUrl: "templates/questionnaire.html",
                    controller: 'QuestionnaireCOntroller'
                })


                .state('questionnaire.question', {
                    url: '/question/:qnum',
                    views: {
                        'menuContent': {
                            template: function () {
                                return '<ion-view><ion-content><div question-item data="data"></div></ion-content></ion-view>'
                            },
                            controller: 'QuestionController'
                        }
                    }
                })



        $urlRouterProvider.otherwise('/login');
    });

    app.controller('HomeTabCtrl', function () {

    })

    app.controller('ListQuestionController', function ($scope) {
        $scope.questions = ["q1", "q2"];
        $scope.c = function () {
            alert("sd")
        };
    });

    app.controller('coba', function ($scope, $state) {
        $scope.leftButtons = [{
                type: 'button-icon icon ion-navicon',
                tap: function (e) {
                    $scope.toggleMenuLeft();
                }
            }];
    });

    app.controller('QuestionnaireCOntroller', function ($scope, $ionicSideMenuDelegate) {

        $scope.toggleMenuLeft = function () {
            $ionicSideMenuDelegate.toggleLeft();

        }
    });

    var choice_list = {
        jenis_kelamin: [{
                name: "1",
                label: "laki-laki"
            }, {
                name: "2",
                label: "perempuan"
            }],
        pekerjaan: [{
                name: "tni",
                label: "TNI"
            }, {
                name: "polri",
                label: "POLRI"
            },
            {
                name: "swasta",
                label: "SWASTA"
            }
        ]
    };
    var question_list = [{
            "name": "B1R1",
            "type": "text",
            "label": "nama"
        }, {
            "name": "B1R2",
            "type": "text",
            "label": "alamat"
        }, {
            "name": "B1R3",
            "type": "select_one",
            "label": "jenis kelamin",
            "option": {
                "choice": "jenis_kelamin"
            }
        }, {
            "name": "B1R4",
            "type": "select_multiple",
            "label": "umur",
            "option": {
                "choice": "jenis_kelamin"
            }
        },
        {
            "name": "B1R4a",
            "type": "select_multiple",
            "label": "umur",
            "option": {
                "choice": "pekerjaan"
            }
        },
        {
            "name": "B1R5",
            "type": "text",
            "label": "alamat"
        },
        {
            "name": "B1R6",
            "type": "text",
            "label": "alamat"
        },
        
    ];
    var answer_list = {};

    app.controller('QuestionController', function ($scope, $stateParams) {
        $scope.data = question_list[$stateParams.qnum];
    });

    app.directive("questionItem", function ($compile) {
        return {
            scope: {
                data: "="
            },
            templateUrl: 'templates/question.html',
            link: function (scope, e, attrs) {
                a = "";
                qae = e.find(".question-answer");
                html = "";
                switch (scope.data.type) {
                    case "integer":
                        {
                            ne = document.createElement("div");
                            html = angular.element(ne);
                            input = angular.element("<input/>", {
                                type: "number",
                                name: "optionsRadios"
                            });
                            html.append(input);
                        }
                        break;
                    case "text":
                        {
                            ne = document.createElement("div");
                            html = angular.element(ne);
                            input = angular.element("<input/>", {
                                type: "text",
                                name: "optionsRadios",
                                'ng-model': "value"
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
                                    type: "radio",
                                    name: "optionsRadios",
                                    value: choice.name,
                                    'ng-model':"value"
                                });
                                label = angular.element("<label/>");
                                label.append(input);
                                label.append(choice.label);
                                div = angular.element("<div/>", {
                                    class: "radio"
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
                                    type: "checkbox",
                                    name: "optionsRadios",
                                    value: choice.name,
                                    'ng-model': "value['"+choice.name+"']",
                                    'ng-change': "change('"+choice.name+"',value"+choice.name+")"
                                });
                                label = angular.element("<label/>");
                                label.append(input);
                                label.append(choice.label);
                                div = angular.element("<div/>", {
                                    class: "checkbox"
                                });
                                div.append(label);
                                html.append(div);
                            }
                        }
                        break;

                }
                qae.html(html);
                $compile(qae.contents())(scope);
            },
            controller: function ($scope, $state, $stateParams) {
                var answer = '';
                var type = $scope.data.type;
                switch (type) {
                    case "integer":
                        {

                        }
                        break;
                    case "text":
                        {
                            $scope.value = "";
                            $scope.$watch('value', function (nv, ov) {
                                answer = nv;
                            });
                            if (answer_list[$scope.data.name] != null) {
                                $scope.value = answer_list[$scope.data.name];
                            }
                        }
                        break;
                    case "select_one":
                        {
                            $scope.value = "";
                            $scope.$watch('value', function (nv, ov) {
                                answer = nv;
                            });
                            if (answer_list[$scope.data.name] != null) {
                                $scope.value = answer_list[$scope.data.name];
                            }
                        }
                        break;
                    case "select_multiple":
                        {
                             $scope.value={};
                            
                            $scope.change = function (choice, status) {
                                answer= $scope.value;
                                console.log( $scope.value);
//                                if(status){
//                                    answer.push(choice);
//                                }else{
//                                    answer.slice(answer.indexOf(choice),1);
//                                }
                            };
                            if (answer_list[$scope.data.name] != null) {
                                $scope.value = answer_list[$scope.data.name];
                                answer= $scope.value;
                            }
                        }
                        break;
                }

                $scope.next = function () {
                    answer_list[$scope.data.name] = answer;
                    console.log(answer_list)
                    $state.go('questionnaire.question', {qnum: parseInt($stateParams.qnum) + 1});
                };

                $scope.back = function () {
                    console.log(answer_list)
                    $state.go('questionnaire.question', {qnum: ($stateParams.qnum - 1)});
                };
            }
        };
    });


})()
