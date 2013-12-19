angular
    .module("simplePrpApp", [ 'pascalprecht.translate' ])

    /**
     * App Config
     */
    .config(
        [
            '$translateProvider',
            function($translateProvider) {

              // German Language
              $translateProvider
                  .translations(
                      'de_DE',
                      {
                        USER_UPDATE_RESPONSE_CODE_0 : 'Deine Profildaten wurden erfolgreich aktualisiert.',
                        USER_UPDATE_RESPONSE_CODE_100 : 'Der Benutzer konnte für die Aktualisierung nicht gelesen werden.',
                        USER_UPDATE_RESPONSE_CODE_110 : 'Es existiert bereits ein Benutzer mit dieser Email-Adresse.'
                      });

              // English Language
              $translateProvider
                  .translations(
                      'en',
                      {
                        USER_UPDATE_RESPONSE_CODE_0 : 'Your profile has been successfully updated.',
                        USER_UPDATE_RESPONSE_CODE_100 : 'The user could not be read for the update.',
                        USER_UPDATE_RESPONSE_CODE_110 : 'A user with this email address exists already.'
                      });

              // Use German as default language
              $translateProvider.preferredLanguage('de_DE');

            } ])

    /**
     * App User Controller
     */
    .controller('UserCtrl', function($scope, $http) {

      // stores the principal of the current user
      var curUserName = "";
      var curUserData = null;

      /**
       * Initializes the dialog with the user data based on the username stored
       * in the principal of the http header.
       */
      $scope.init = function(principal) {
        curUserName = principal;
        var url = "/simpleprp/users/byUsername/" + principal;
        $http.get(url).success(function(data) {
          $scope.user = data;
          this.curUserData = data;
          $scope.retCode = "";
        }).error(function(data) {
          $scope.user = this.curUserData;
          var errorCode = data.code * -1;
          $scope.retCode = errorCode.toString();
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
          $scope.retCode = "0";
        }).error(function(data) {
          $scope.user = this.curUserData;
          var errorCode = data.code * -1;
          $scope.retCode = errorCode.toString();
        });
      };
    });
