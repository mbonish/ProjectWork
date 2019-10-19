/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Game;
import com.sg.mastermind.entity.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author MARIA
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundDaoDBImplTest {
    @Autowired 
    GameDao gameDao;
  
    @Autowired
   RoundDao roundDao;
   
    
    public RoundDaoDBImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         
       List<Game> games =gameDao.getAllGames();
        for(Game g: games){
            gameDao.deleteGame(g.getGameId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addRound method, of class RoundDaoDBImpl.
     */
    @Test
    public void testAddRound() {
         Game g = new Game();
        g.setGameId(1);
        g.setGameStatus(true);
        g.setGameKey(1234);
        gameDao.addGame(g);
        
        Round round = new Round();
                round.setGuess(1234);
                round.setGuessTime(LocalDateTime.now());
                round.setGuessResult("e0 : p0");
               round.setGameId(g.getGameId());
        roundDao.addRound(round);
        
        Round DaoRound=roundDao.addRound(round);
        
       
        assertEquals(round, DaoRound);
        
        
    }

    /**
     * Test of getRoundsByGameID method, of class RoundDaoDBImpl.
     */
    @Test
    public void testGetRoundsByGameID() {
        
        
    }
    
}
