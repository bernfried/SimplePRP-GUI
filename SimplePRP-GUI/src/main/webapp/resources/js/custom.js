var simplePrpApp = angular.module("simplePrpApp", []);

simplePrpApp.controller('UserCtrl', function($scope, $http) {

	// stores the principal of the current user
	var curUserName = "";
	var curUserData = null;

	/**
	 * Initializes the dialog with the user data based on the username stored in
	 * the principal of the http header.
	 */
	$scope.init = function(principal) {
		curUserName = principal;
		var url = "/simpleprp/users/byUsername/" + principal;
		$http.get(url).success(function(data) {
			$scope.user = data;
			this.curUserData = data;
			$scope.exceptionResponse = null;
		}).error(function(data) {
			$scope.user = this.curUserData;
			$scope.exceptionResponse = data;
		});
	};
	
	/**
	 * Saves the user profile data of the current user
	 */
	$scope.save = function() {
		$scope.user.changedBy = curUserName;
		$scope.user.changedAt = new Date();
		$http.put('/simpleprp/users/', angular.toJson($scope.user), {
			headers : {
				'Content-Type' : 'application/json; charset=UTF-8'
			}
		}).success(function(data) {
			$scope.user = data;
			this.curUserData = data;
			$scope.exceptionResponse = {"code":0,"description":"User Profile has been successfully stored."};
		}).error(function(data) {
			$scope.user = this.curUserData;
			$scope.exceptionResponse = data;
		});
	};

});
