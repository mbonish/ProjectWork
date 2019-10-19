/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Round;
import java.util.List;

/**
 *
 * @author MARIA
 */
public interface RoundDao {
  // add round
    Round addRound(Round round);
  
    List<Round> getRoundsByGameID(int gameId);
    
  
    
}
