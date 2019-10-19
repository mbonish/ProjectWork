/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Game;
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

@RunWith(SpringRunner.class)
@SpringBootTest

public class GameDaoDBImplTest {
    @Autowired
    GameDao GameDao;
    
    @Autowired
    RoundDao RoundDao;
        
    public GameDaoDBImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       List<Game> games =GameDao.getAllGames();
        for(Game g: games){
            GameDao.deleteGame(g.getGameId());
        }
    }
        
    
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class GameDaoDBImpl.
     */
    @Test
    public void testAddGame() {
       Game g = new Game();
        g.setGameId(1);
        g.setGameStatus(true);
        g.setGameKey(1234);
        GameDao.addGame(g);
        
        Game fromDao = GameDao.getGameById(g.getGameId());
       
        assertEquals(g, fromDao);
        
        assertEquals(g.getGameId(), fromDao.getGameId());
        
         assertEquals(g.isGameStatus(), fromDao.isGameStatus());
         
         assertEquals(g.getGameKey(), fromDao.getGameKey());
    }

   
    @Test
    public void testGetGameById() {
       
         Game g = new Game();
        g.setGameStatus(true);
        g.setGameKey(1234);
        Game game = GameDao.addGame(g);
        
        Game gamedao=GameDao.getGameById(game.getGameId());
        
        assertEquals(gamedao, game);
    }

   
    @Test
    public void testGetAllGames() {
         Game g = new Game();
        g.setGameId(1);
        g.setGameStatus(true);
        g.setGameKey(1234);
        GameDao.addGame(g);
        
         Game g2 = new Game();
        g2.setGameId(1);
        g2.setGameStatus(true);
        g2.setGameKey(1234);
        GameDao.addGame(g2);
        
       List<Game> games= GameDao.getAllGames();
       assertEquals(games.size(), 2);
        
    }

    /**
     * Test of updateGame method, of class GameDaoDBImpl.
     */
    @Test
    public void testUpdateGame() {
         Game g = new Game();
        g.setGameId(1);
        g.setGameStatus(true);
        g.setGameKey(1234);
        GameDao.addGame(g);
        
        
        GameDao.updateGame(g);
        g.setGameStatus(false);
        
        assertEquals(g.isGameStatus(),false);
    }
    
}
