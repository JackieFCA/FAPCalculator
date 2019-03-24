var app = angular.module("app", []);

app.controller("calculator", function ($scope, $http, $location) {
    
    $scope.submitForm = function () {

        var url = $location.absUrl() + "calculator";

        var init = {
            numAdpack: $scope.numAdpack,
            duration: $scope.duration,
            reinvest: $scope.reinvest
        }

        $http.post(url, init).then(function (response) {
            $scope.results = response.data;
            console.log(results);
		}, function error(response) {
			$scope.postResultMessage = "Error with status: " +  response.statusText;
		});
    }
});