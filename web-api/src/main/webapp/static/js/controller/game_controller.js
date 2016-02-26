'use strict';
 
App.controller('GameController', ['$scope', 'GameService', function($scope, GameService) {
          var self = this;
          self.game={id:null,name:'', devCompanyName:'', description:''};
          self.games=[];
               
          self.fetchAllGames = function(){
              GameService.fetchAllGames()
                  .then(
                               function(d) {
                                    self.users = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Currencies');
                                }
                       );
          };
            
          self.createGame = function(game){
              GameService.createGame(game)
                      .then(
                      self.fetchAllGames, 
                              function(errResponse){
                                   console.error('Error while creating Game.');
                              } 
                  );
          };
 
         self.updateGame = function(game, id){
              GameService.updateUser(game, id)
                      .then(
                              self.fetchAllGames, 
                              function(errResponse){
                                   console.error('Error while updating Games.');
                              } 
                  );
          };
 
         self.deleteGames = function(id){
              GameService.deleteGame(id)
                      .then(
                              self.fetchAllGames, 
                              function(errResponse){
                                   console.error('Error while deleting Game.');
                              } 
                  );
          };
 
          self.fetchAllGames();
 
          self.submit = function() {
              if(self.game.id===null){
                  console.log('Saving New Game', self.game);    
                  self.createGame(self.game);
              }else{
                  self.updateGame(self.game, self.game.id);
                  console.log('Game updated with id ', self.game.id);
              }
              self.reset();
          };
               
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.games.length; i++){
                  if(self.games[i].id === id) {
                     self.game = angular.copy(self.games[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.game.id === id) {//clean form if the user to be deleted is shown there.
                 self.reset();
              }
              self.deleteGame(id);
          };
 
           
          self.reset = function(){
              self.game={id:null,name:'',devCompanyName:'',description:''};
              $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);