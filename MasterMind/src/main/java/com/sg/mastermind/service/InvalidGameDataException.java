/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.service;

/**
 *
 * @author MARIA
 */
public class InvalidGameDataException extends Exception {

    public InvalidGameDataException(String message) {
        super(message);
    }

    public InvalidGameDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
