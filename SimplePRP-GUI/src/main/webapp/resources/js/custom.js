var simplePrpApp = angular.module("simplePrpApp", []);

simplePrpApp.controller('UserCtrl', function($scope, $http) {
	
	$scope.init = function(principal) {
		var url = "/simpleprp/users/byUsername/" + principal; 
		$http.get(url).success(function(data) {
			$scope.user = data;
		});
	};
	
	$scope.save = function() {
		alert("Save user...");
	};
	
});
