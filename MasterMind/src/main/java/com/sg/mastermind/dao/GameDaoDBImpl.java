/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MARIA
 */
@Repository
public class GameDaoDBImpl implements GameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Game addGame(Game game) {
        jdbc.update("INSERT INTO game(gameStatus, gameKey) VALUES(?,?)",
                game.isGameStatus(),
                game.getGameKey());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        return game;
    }

    @Override
    public Game getGameById(int gameId) {
        try {
            Game g = jdbc.queryForObject("SELECT * FROM game WHERE gameId = ?", new GameMapper(), gameId);
            return g;
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = jdbc.query("SELECT * FROM game", new GameMapper());
        return games;
    }

    @Override
    public void updateGame(Game game) {
        jdbc.update("UPDATE game SET gameStatus = ? WHERE gameid = ?",
                game.isGameStatus(),
                game.getGameId());
    }

    
    @Override
    @Transactional 
    public void deleteGame(int gameId) {
        final String DELETE_GAME_BY_ROUND = "DELETE FROM round WHERE gameid = ? ";
        jdbc.update(DELETE_GAME_BY_ROUND, gameId);
        
        final String DELETE_GAME ="DELETE FROM game WHERE gameid = ?";
      jdbc.update(DELETE_GAME,gameId);
        
    }

    
    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game g = new Game();
            g.setGameId(rs.getInt("gameId"));
            g.setGameStatus(rs.getBoolean("gameStatus"));
            g.setGameKey(rs.getInt("gameKey"));
            return g;
        }

    }
}
