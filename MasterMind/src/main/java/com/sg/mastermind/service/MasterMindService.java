/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.service;

import com.sg.mastermind.dao.GameDao;
import com.sg.mastermind.dao.RoundDao;
import com.sg.mastermind.entity.Game;
import com.sg.mastermind.entity.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MARIA
 */
@Service
public class MasterMindService {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public int gameKeyGenerator() {

        Random r = new Random();
        r.nextInt(10);

        int[] arr = new int[4];
        int one = r.nextInt(10);
        int two = 0;
        int three = 0;
        int four = 0;

        arr[0] = one;

        while (two == one) {
            two = r.nextInt(10);
        }
        arr[1] = two;
        while (three == one || three == two) {
            three = r.nextInt(10);
        }
        arr[2] = three;
        while (four == one || four == two || four == three) {
            four = r.nextInt(10);
        }
        arr[3] = four;

        return arr[0] *1000 + 
                arr[1] *100 +
                arr[2] *10 +
                arr[3];

    }

    public String guessResult(int guess, int gameId) {
        int exact = 0;
        int partial = 0;
        int[] guessArr = new int[4];

        guessArr[3] = guess % 10;
        guessArr[2] = (guess / 10) % 10;
        guessArr[1] = (guess / 100) % 10;
        guessArr[0] = (guess / 1000) % 10;


        Game game = gameDao.getGameById(gameId);
        int gameKeyInt =game.getGameKey();
        int [] gameKey = new int[4];
        
        gameKey[3] = gameKeyInt %10;
        gameKey[2] = (gameKeyInt/10) %10;
        gameKey[1] = (gameKeyInt/100)%10;
        gameKey[0] = (gameKeyInt/1000) %10;
        
 

        if (guessArr[0] == gameKey[0]) {
            exact++;
        }
        if (guessArr[0] == gameKey[1]
                || guessArr[0] == gameKey[2]
                || guessArr[0] == gameKey[3]) {
            partial++;
        }
        if (guessArr[1] == gameKey[1]) {
            exact++;
        }
        if (guessArr[1] == gameKey[0]
                || guessArr[1] == gameKey[2]
                || guessArr[1] == gameKey[3]) {
            partial++;
        }
        if (guessArr[2] == gameKey[2]) {
            exact++;
        }
        if (guessArr[2] == gameKey[0]
                || guessArr[2] == gameKey[1]
                || guessArr[2] == gameKey[3]) {
            partial++;
        }
        if (guessArr[3] == gameKey[3]) {
            exact++;
        }
        if (guessArr[3] == gameKey[1]
                || guessArr[3] == gameKey[2]
                || guessArr[3] == gameKey[0]) {
            partial++;
        }

        String roundResult = "e: " + exact + ":" + "p: " + partial;

        return roundResult;
    }

    //add in validation for number of didgits 
//method to get the roundid based off of the gameid
    /*public int getRoundId(int gameId) {

        List<Round> rounds = roundDao.getRoundsByGameID(gameId);

        int highestRound = 0;
        for (Round round : rounds) {
            if (round.getRoundId() > highestRound) {
                highestRound = round.getRoundId();
            }

        }

        return highestRound++;
    }
*/
  public Game addGame(Game game){
      return gameDao.addGame(game);
      
  }

   public Game getGameById(int gameid){
        return gameDao.getGameById(gameid);
    }

    public List<Game> getAllGames(){
        return gameDao.getAllGames();
    }

   public  void updateGame(Game game){
        gameDao.updateGame(game);
        
    }
    
    public Round addRound(Round round) throws InvalidGameDataException{
      Game g = getGameById(round.getGameId());
     if (g.isGameStatus()== true){ //true complete
         throw new InvalidGameDataException("Game is complete");
     }
        
     return roundDao.addRound(round);
    }
    
    public List<Round> getRoundsByGameID(int gameId){
      return roundDao.getRoundsByGameID(gameId);
    }
     
    
}
