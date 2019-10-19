/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.controller;

import com.sg.mastermind.entity.Game;
import com.sg.mastermind.entity.Round;
import com.sg.mastermind.service.InvalidGameDataException;
import com.sg.mastermind.service.MasterMindService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MARIA
 */
@RestController
public class MasterMindController {
    
    @Autowired
    MasterMindService service;
    

        
        @PostMapping ("begin") 
        public ResponseEntity<Integer> startGame(){
        Game g = new Game();
        g.setGameStatus(false);
        g.setGameKey(service.gameKeyGenerator());
       Game startGame= service.addGame(g);
       return ResponseEntity.ok(startGame.getGameId());
        }
        
       @PostMapping("guess")
       public ResponseEntity<Round> guessPerRound(@RequestBody Round round) throws InvalidGameDataException{
          
           String result =service.guessResult(round.getGuess(), round.getGameId());
           round.setGuessResult(result);
           round.setGuessTime(LocalDateTime.now());
           Round r= service.addRound(round);
           if(result.charAt(3)== '4'){
               Game game= service.getGameById(round.getGameId());
              game.setGameStatus(true);
              service.updateGame(game);        
           }
           return ResponseEntity.ok(r);
           
        }
       
       @GetMapping("game")
       public ResponseEntity <List<Game>> listOfGames(){
          List <Game> games = service.getAllGames();
         for(Game game:games){
             if(game.isGameStatus()==false)
                 game.setGameKey(0);
         }
         return ResponseEntity.ok(games);
    }
       
    @GetMapping ("/{gameId}")
          public ResponseEntity <Game> retreiveSingleGame(@PathVariable int gameId){
              Game game =service.getGameById(gameId);
              if(game == null){
                  return new ResponseEntity (null, HttpStatus.NOT_FOUND);
              }
              if(game.isGameStatus()==false){
                  game.setGameKey(0);
              }
              return ResponseEntity.ok(game);
          }

  
    @GetMapping ("rounds/{gameId}")
    public ResponseEntity <List<Round>> retrieveRoundsByGameId(@PathVariable int gameId){
        List <Round> rounds = service.getRoundsByGameID(gameId);
        return ResponseEntity.ok(rounds);
    }
    
  
   
}
