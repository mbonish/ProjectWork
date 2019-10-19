/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARIA
 */
@Repository
public class RoundDaoDBImpl implements RoundDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Round addRound(Round round){
    jdbc.update("INSERT INTO round( guess, guessTime, guessResult, gameId)"
                + "VALUES (?,?,?,?)",
                round.getGuess(),
                round.getGuessTime(),
                round.getGuessResult(),
                round.getGameId());
    int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    round.setRoundId(newId);
        return round;
     
                            
    }

    @Override
    public List<Round> getRoundsByGameID(int gameId) {
       
        List<Round> rounds = jdbc.query("SELECT * FROM round WHERE gameId = ? order By guessTime", new 
RoundMapper(),
                gameId);
        return rounds;
       
}
    
    public static final class RoundMapper implements RowMapper<Round>{

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round r = new Round();
            r.setRoundId(rs.getInt("roundId"));
            r.setGuess(rs.getInt("guess"));
            r.setGuessTime(rs.getTimestamp("guessTime").toLocalDateTime());
            r.setGuessResult(rs.getString("guessResult"));
            r.setGameId(rs.getInt("gameId"));
           return r;
            
        }
      

         

        
    }
}
