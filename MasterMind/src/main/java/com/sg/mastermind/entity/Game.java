/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.entity;

/**
 *
 * @author MARIA
 */
public class Game {
    int gameId;
    boolean gameStatus;
    int gameKey;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean isGameStatus() {
        return gameStatus; 
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getGameKey() {
        return gameKey;
    }

    public void setGameKey(int gameKey) {
        this.gameKey = gameKey;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.gameId;
        hash = 59 * hash + (this.gameStatus ? 1 : 0);
        hash = 59 * hash + this.gameKey;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.gameStatus != other.gameStatus) {
            return false;
        }
        if (this.gameKey != other.gameKey) {
            return false;
        }
        return true;
    }
    
}
