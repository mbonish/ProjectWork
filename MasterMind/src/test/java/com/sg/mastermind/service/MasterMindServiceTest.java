/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.service;

import com.sg.mastermind.entity.Game;
import com.sg.mastermind.entity.Round;
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
public class MasterMindServiceTest {
    @Autowired 
    MasterMindService service;
    public MasterMindServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of gameKeyGenerator method, of class MasterMindService.
     */
    @Test
    public void testGameKeyGenerator() {
        
       int result =service.gameKeyGenerator();
       assertNotNull(result);
       assertTrue(result <10000 );
       assertTrue(result>122);
      
       assertNotSame(result %10, (result/10) %10);
       assertNotSame(result %10, (result/100) %10);
       assertNotSame(result %10, (result/1000) %10);
       
        assertNotSame((result/10) %10, (result %10));
        assertNotSame((result/10) %10, (result/100) %10);
        assertNotSame((result/10) %10, (result %1000) %10);
        
        assertNotSame((result/100) %10, (result %10));
        assertNotSame((result/100) %10, (result/10) %10);
        assertNotSame((result/100) %10, (result %1000) %10); 
        
        assertNotSame((result/1000) %10, (result %10));
        assertNotSame((result/1000) %10, (result/10) %10);
        assertNotSame((result/1000) %10, (result %100) %10);
        
         
       
    }

    /**
     * Test of guessResult method, of class MasterMindService.
     */
    @Test
    public void testGuessResult() {
        Game g = new Game();
        g.setGameKey(1234);
        g.setGameStatus(true);
        Game game =service.addGame(g);
        
        Round r = new Round();
        r.setGuess(1234);
        
        
       String result = service.guessResult( r.getGuess(),game.getGameId());
        assertEquals(result, "e: 4:p: 0");
        
        
    }

    /**
     * Test of getRoundId method, of class MasterMindService.
     */
    @Test
    public void testGetRoundId() {
        //passthrough
    }

    /**
     * Test of addGame method, of class MasterMindService.
     */
    @Test
    public void testAddGame() {
        //pass through
    }

    /**
     * Test of getGameById method, of class MasterMindService.
     */
    @Test
    public void testGetGameById() {
        //pass through
    }

    /**
     * Test of getAllGames method, of class MasterMindService.
     */
    @Test
    public void testGetAllGames() {
        //pass through 
    }

    /**
     * Test of updateGame method, of class MasterMindService.
     */
    @Test
    public void testUpdateGame() {
        //pass through
    }

    /**
     * Test of addRound method, of class MasterMindService.
     */
    @Test
    public void testAddRound() throws Exception {
        Game g = new Game();
        g.setGameKey(1234);
        g.setGameStatus(true);
        service.addGame(g);
        
        Round r = new Round();
        r.setGuess(1234);
        r.setGameId(g.getGameId());
        
        
        try{
            service.addRound(r);
          fail("Expected InvalidGameDataException to be thrown");  
        }catch(InvalidGameDataException ex){
    }
    }

    /**
     * Test of getRoundsByGameID method, of class MasterMindService.
     */
    @Test
    public void testGetRoundsByGameID() {
        //through
    }
    
}
