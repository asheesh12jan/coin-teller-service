package com.asheesh.cointellerservice.exception;

import com.asheesh.cointellerservice.dto.CoinTellerError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CoinTellerExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex){
        CoinTellerError error = new CoinTellerError(400, ex.getMessage());
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(CoinTellerServiceException.class)
    public ResponseEntity<Object> handleCoinTellerServiceException(CoinTellerServiceException ex){
        return ResponseEntity.status(ex.getHttpStatusCode()).body(ex.createErrorResponse());
    }
}
