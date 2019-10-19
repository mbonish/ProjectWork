/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.dao;

import com.sg.mastermind.entity.Game;
import java.util.List;

/**
 *
 * @author MARIA
 */
public interface GameDao {

    Game addGame(Game game);

    Game getGameById(int gameid);

    List<Game> getAllGames();

    void updateGame(Game game);

    void deleteGame(int gameId);
}
