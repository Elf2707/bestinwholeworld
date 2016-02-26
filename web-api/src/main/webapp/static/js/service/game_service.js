'use strict';
 
App.factory('GameService', ['$http', '$q', function($http, $q){
 
    return {
         
            fetchAllGames: function() {
                    return $http.get('http://localhost:8080/api/games/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching games');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            createGame: function(game){
                    return $http.post('http://localhost:8080/api/game/add', game)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating game');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            updateGame: function(game, id){
                    return $http.put('http://localhost:8080/api/game/'+id, game)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating game');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            deleteGame: function(id){
                    return $http.delete('http://localhost:8080/api/game/'+id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting game');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
         
    };
 
}]);