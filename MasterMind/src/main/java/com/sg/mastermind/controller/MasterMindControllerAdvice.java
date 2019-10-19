/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mastermind.controller;

import com.sg.mastermind.service.InvalidGameDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author MARIA
 */

    @ControllerAdvice
@RestController
public class MasterMindControllerAdvice {

        @ExceptionHandler(InvalidGameDataException.class)
    public ResponseEntity<String> handleInvalidDataException(InvalidGameDataException ex,
            WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

