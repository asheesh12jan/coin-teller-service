package com.asheesh.cointellerservice.exception;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends CoinTellerServiceException {

    public InvalidInputException(String msg){
        super(msg);
    }

    @Override
    public int getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public Boolean isRetryable() {
        return false;
    }
}
